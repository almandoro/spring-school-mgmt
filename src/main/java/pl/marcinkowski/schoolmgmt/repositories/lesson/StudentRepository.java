package pl.marcinkowski.schoolmgmt.repositories.lesson;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.marcinkowski.schoolmgmt.entities.schoolclass.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
