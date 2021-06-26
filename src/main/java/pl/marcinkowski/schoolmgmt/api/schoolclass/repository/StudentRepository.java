package pl.marcinkowski.schoolmgmt.api.schoolclass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.marcinkowski.schoolmgmt.api.schoolclass.entity.Student;
import pl.marcinkowski.schoolmgmt.api.user.entity.User;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student getByUser(User user);
}
