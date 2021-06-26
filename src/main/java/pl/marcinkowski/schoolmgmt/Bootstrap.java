package pl.marcinkowski.schoolmgmt;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.marcinkowski.schoolmgmt.entities.lesson.Lesson;
import pl.marcinkowski.schoolmgmt.entities.lesson.LessonSubject;
import pl.marcinkowski.schoolmgmt.entities.lesson.Teacher;
import pl.marcinkowski.schoolmgmt.entities.schoolclass.SchoolClass;
import pl.marcinkowski.schoolmgmt.entities.schoolclass.Student;
import pl.marcinkowski.schoolmgmt.entities.user.Role;
import pl.marcinkowski.schoolmgmt.entities.user.User;
import pl.marcinkowski.schoolmgmt.entities.user.UserRole;
import pl.marcinkowski.schoolmgmt.repositories.classgroup.ClassTeacherRepository;
import pl.marcinkowski.schoolmgmt.repositories.classgroup.SchoolClassRepository;
import pl.marcinkowski.schoolmgmt.repositories.lesson.LessonRepository;
import pl.marcinkowski.schoolmgmt.repositories.lesson.StudentRepository;
import pl.marcinkowski.schoolmgmt.repositories.user.UserRepository;
import pl.marcinkowski.schoolmgmt.repositories.user.UserRoleRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class Bootstrap {

  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;
  private final UserRoleRepository userRoleRepository;
  private final ClassTeacherRepository classTeacherRepository;
  private final SchoolClassRepository schoolClassRepository;
  private final StudentRepository studentRepository;
  private final LessonRepository lessonRepository;

  public Bootstrap(
      PasswordEncoder passwordEncoder,
      UserRepository userRepository,
      UserRoleRepository userRoleRepository,
      ClassTeacherRepository classTeacherRepository,
      SchoolClassRepository schoolClassRepository,
      StudentRepository studentRepository,
      LessonRepository lessonRepository) {
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
    this.userRoleRepository = userRoleRepository;
    this.classTeacherRepository = classTeacherRepository;
    this.schoolClassRepository = schoolClassRepository;
    this.studentRepository = studentRepository;
    this.lessonRepository = lessonRepository;
  }

  @EventListener(ApplicationReadyEvent.class)
  public void bootstrapData() {

    System.out.println(" --- Bootstrap DB with Dummy Data ---");
    createRolePermissionsEntities();
    createAdmins();
    createTeachersAndLessons();
    createStudents();
    createSchoolClasses();
    System.out.println(" --- DB Loaded ---");

    System.out.println("Loaded Users - " + userRepository.count());
    System.out.println("Loaded Roles - " + userRoleRepository.count());
    System.out.println("Loaded Teachers - " + classTeacherRepository.count());
    System.out.println("Loaded Classes - " + schoolClassRepository.count());
    System.out.println("Loaded Students - " + studentRepository.count());
  }

  private void createRolePermissionsEntities() {
    Arrays.stream(Role.values())
        .forEach(
            role -> {
              userRoleRepository.save(new UserRole(role));
            });
  }

  private void createAdmins() {
    List<UserRole> adminRoles = userRoleRepository.findAllByRole(Role.ROLE_ADMIN);
    List<User> admins =
        Stream.of(
                new User(
                    "Maciej",
                    "Marcinkowski",
                    "marcinkowski@dev.com",
                    passwordEncoder.encode("dev"),
                    adminRoles),
                new User(
                    "Admin",
                    "admin",
                    "admin@admin.com",
                    passwordEncoder.encode("admin"), // admin:admin for the win =)
                    adminRoles))
            .collect(Collectors.toList());

    userRepository.saveAll(admins);
  }

  private void createTeachersAndLessons() {
    List<UserRole> teacherRoles = userRoleRepository.findAllByRole(Role.ROLE_TEACHER);

    LessonSubject[] subjects = LessonSubject.values();
    for (LessonSubject subject : subjects) {
      User tUser =
          new User(
              "Teacher",
              subject.toString(),
              subject.toString() + "@teacher.com",
              "teacher",
              teacherRoles);
      userRepository.save(tUser);

      Teacher teacher = new Teacher(tUser);
      classTeacherRepository.save(teacher);
      Lesson lesson = new Lesson(subject, teacher);
      lessonRepository.save(lesson);
    }
  }

  private void createStudents() {
    List<UserRole> studentRoles = userRoleRepository.findAllByRole(Role.ROLE_STUDENT);
    List<User> students =
        Stream.of(
                new User(
                    "Student",
                    "Poor",
                    "student2@student.com",
                    passwordEncoder.encode("student"),
                    studentRoles),
                new User(
                    "DefNotStudent",
                    "Null",
                    "student2@student.com",
                    passwordEncoder.encode("student"),
                    studentRoles))
            .collect(Collectors.toList());

    List<Student> collectedStudents =
        userRepository.saveAll(students).stream().map(Student::new).collect(Collectors.toList());

    studentRepository.saveAll(collectedStudents);
  }

  private void createSchoolClasses() {
    List<Teacher> teachers = classTeacherRepository.findAll();
    int teachersCount = teachers.size();

    for (int grade = 1; grade <= 5; grade++) {
      List<SchoolClass> schoolClasses = new ArrayList<>();
      List<Student> students = new ArrayList<>();

      for (char c = 'A'; c <= 'A' + teachersCount; ++c) {
        String className = String.valueOf(c);

        SchoolClass sClass = new SchoolClass(grade, className);
        schoolClasses.add(sClass);

        List<UserRole> studentRoles = userRoleRepository.findAllByRole(Role.ROLE_STUDENT);
        User sUser =
            new User(
                "Student",
                className + grade,
                "s" + className + grade + "@student.com",
                "student",
                studentRoles);
        userRepository.save(sUser);

        Student student = new Student(schoolClasses, sUser);
        students.add(student);
        studentRepository.save(student);

        for (SchoolClass schoolClass : schoolClasses) {
          schoolClass.setStudents(students);
        }
      }

      schoolClassRepository.saveAll(schoolClasses);
    }
  }
}
