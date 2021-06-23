package pl.marcinkowski.schoolmgmt;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.marcinkowski.schoolmgmt.entities.user.Role;
import pl.marcinkowski.schoolmgmt.entities.user.User;
import pl.marcinkowski.schoolmgmt.entities.user.UserRole;
import pl.marcinkowski.schoolmgmt.repositories.classgroup.ClassTeacherRepository;
import pl.marcinkowski.schoolmgmt.repositories.classgroup.SchoolClassRepository;
import pl.marcinkowski.schoolmgmt.repositories.user.UserRepository;
import pl.marcinkowski.schoolmgmt.repositories.user.UserRoleRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class Bootstrap {

  private final UserRepository userRepository;
  private final UserRoleRepository userRoleRepository;
  private final ClassTeacherRepository classTeacherRepository;
  private final SchoolClassRepository schoolClassRepository;
  private final PasswordEncoder passwordEncoder;

  public Bootstrap(
      UserRepository userRepository,
      UserRoleRepository userRoleRepository,
      ClassTeacherRepository classTeacherRepository,
      SchoolClassRepository schoolClassRepository,
      PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.userRoleRepository = userRoleRepository;
    this.classTeacherRepository = classTeacherRepository;
    this.schoolClassRepository = schoolClassRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @EventListener(ApplicationReadyEvent.class)
  public void bootstrapData() {

    createRolePermissionsEntities();
    createAdmins();
    createTeachers();
    createStudents();


    System.out.println(" --- Bootstrapped API with Dummy Data ---");
    System.out.println("Loaded Users - " + userRepository.count());
    System.out.println("Loaded Roles - " + userRoleRepository.count());
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

  private void createTeachers() {
    List<UserRole> teacherRoles = userRoleRepository.findAllByRole(Role.ROLE_TEACHER);
    List<User> teachers =
        Stream.of(
                new User(
                    "Maciej",
                    "Nauczyciel",
                    "marcinkowski_nauczyciel@dev.com",
                    passwordEncoder.encode("dev"),
                    teacherRoles),
                new User(
                    "Teacher",
                    "Teacherovsky",
                    "teach@me.com",
                    passwordEncoder.encode("teach3r"),
                    teacherRoles))
            .collect(Collectors.toList());

    userRepository.saveAll(teachers);
  }

  private void createStudents() {
    List<UserRole> studentRoles = userRoleRepository.findAllByRole(Role.ROLE_STUDENT);
    List<User> teachers =
        Stream.of(
                new User(
                    "Student",
                    "Poor",
                    "poor_student@student.com",
                    passwordEncoder.encode("student"),
                    studentRoles),
                new User(
                    "DefNotStudent",
                    "Null",
                    "not_student@student.com",
                    passwordEncoder.encode("student0"),
                    studentRoles))
            .collect(Collectors.toList());

    userRepository.saveAll(teachers);
  }

  private void createSchoolClasses() {}
}
