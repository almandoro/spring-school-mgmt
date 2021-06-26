package pl.marcinkowski.schoolmgmt;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.marcinkowski.schoolmgmt.api.lesson.entity.Lesson;
import pl.marcinkowski.schoolmgmt.api.lesson.entity.LessonSubject;
import pl.marcinkowski.schoolmgmt.api.lesson.entity.ScheduledLesson;
import pl.marcinkowski.schoolmgmt.api.lesson.entity.Teacher;
import pl.marcinkowski.schoolmgmt.api.schoolclass.entity.SchoolClass;
import pl.marcinkowski.schoolmgmt.api.schoolclass.entity.Student;
import pl.marcinkowski.schoolmgmt.api.user.entity.User;
import pl.marcinkowski.schoolmgmt.api.user.entity.UserRole;
import pl.marcinkowski.schoolmgmt.api.lesson.repository.TeacherRepository;
import pl.marcinkowski.schoolmgmt.api.schoolclass.repository.SchoolClassRepository;
import pl.marcinkowski.schoolmgmt.api.lesson.repository.LessonRepository;
import pl.marcinkowski.schoolmgmt.api.lesson.repository.ScheduledLessonRepository;
import pl.marcinkowski.schoolmgmt.api.schoolclass.repository.StudentRepository;
import pl.marcinkowski.schoolmgmt.api.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class Bootstrap {

  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;
  private final TeacherRepository teacherRepository;
  private final SchoolClassRepository schoolClassRepository;
  private final StudentRepository studentRepository;
  private final LessonRepository lessonRepository;
  private final ScheduledLessonRepository scheduledLessonRepository;

  public Bootstrap(
      PasswordEncoder passwordEncoder,
      UserRepository userRepository,
      TeacherRepository teacherRepository,
      SchoolClassRepository schoolClassRepository,
      StudentRepository studentRepository,
      LessonRepository lessonRepository,
      ScheduledLessonRepository scheduledLessonRepository) {
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
    this.teacherRepository = teacherRepository;
    this.schoolClassRepository = schoolClassRepository;
    this.studentRepository = studentRepository;
    this.lessonRepository = lessonRepository;
    this.scheduledLessonRepository = scheduledLessonRepository;
  }

  @EventListener(ApplicationReadyEvent.class)
  public void bootstrapData() {

    System.out.println(" --- Bootstrap DB with Dummy Data ---");
    createAdmins();
    createTeachersAndLessons();
    createStudents();
    createSchoolClasses();
    scheduleLessons();
    System.out.println(" --- DB Loaded ---");

    System.out.println("Loaded Users - " + userRepository.count());
    System.out.println("Loaded Teachers - " + teacherRepository.count());
    System.out.println("Loaded Classes - " + schoolClassRepository.count());
    System.out.println("Loaded Students - " + studentRepository.count());
  }

  private void createAdmins() {
    List<User> admins =
        Stream.of(
                new User(
                    "Maciej",
                    "Marcinkowski",
                    "marcinkowski@dev.com",
                    passwordEncoder.encode("dev"),
                    UserRole.ROLE_ADMIN),
                new User(
                    "Admin",
                    "admin",
                    "admin@admin.com",
                    passwordEncoder.encode("admin"), // admin:admin for the win =)
                    UserRole.ROLE_ADMIN))
            .collect(Collectors.toList());

    userRepository.saveAll(admins);
  }

  private void createTeachersAndLessons() {
    LessonSubject[] subjects = LessonSubject.values();
    for (LessonSubject subject : subjects) {
      User tUser =
          new User(
              "Teacher",
              subject.toString(),
              subject.toString() + "@teacher.com",
              passwordEncoder.encode("teacher"),
              UserRole.ROLE_TEACHER);
      userRepository.save(tUser);

      Teacher teacher = new Teacher(tUser);
      teacherRepository.save(teacher);
      Lesson lesson = new Lesson(subject, teacher);
      lessonRepository.save(lesson);
    }
  }

  private void createStudents() {
    List<User> students =
        Stream.of(
                new User(
                    "Student",
                    "Poor",
                    "student@student.com",
                    passwordEncoder.encode("student"),
                    UserRole.ROLE_STUDENT),
                new User(
                    "DefNotStudent",
                    "Null",
                    "student2@student.com",
                    passwordEncoder.encode("student"),
                    UserRole.ROLE_STUDENT))
            .collect(Collectors.toList());

    List<Student> collectedStudents =
        userRepository.saveAll(students).stream().map(Student::new).collect(Collectors.toList());

    studentRepository.saveAll(collectedStudents);
  }

  private void createSchoolClasses() {
    List<Teacher> teachers = teacherRepository.findAll();
    int teachersCount = teachers.size();

    for (int grade = 1; grade <= 5; grade++) {
      List<SchoolClass> schoolClasses = new ArrayList<>();
      List<Student> students = new ArrayList<>();

      for (char c = 'A'; c <= 'A' + teachersCount; ++c) {
        String className = String.valueOf(c);

        SchoolClass sClass = new SchoolClass(grade, className);
        schoolClasses.add(sClass);

        User sUser =
            new User(
                "Student",
                className + grade,
                "s" + className + grade + "@student.com",
                passwordEncoder.encode("student"),
                UserRole.ROLE_STUDENT);
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

  private void scheduleLessons() {
    Lesson lesson = lessonRepository.findAll().get(0);
    SchoolClass schoolClass = schoolClassRepository.findAll().get(0);

    Calendar startDate = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    startDate.set(Calendar.YEAR, 2017);
    startDate.set(Calendar.MONTH, 10);
    startDate.set(Calendar.DAY_OF_MONTH, 15);
    startDate.set(2021, 6, 26, 8, 0);

    Calendar endDate = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    startDate.set(Calendar.YEAR, 2017);
    startDate.set(Calendar.MONTH, 10);
    startDate.set(Calendar.DAY_OF_MONTH, 15);
    startDate.set(2021, 6, 26, 8, 0);

    ScheduledLesson scheduledLesson = new ScheduledLesson(schoolClass, lesson, startDate, endDate);
    scheduledLessonRepository.save(scheduledLesson);

  }
}
