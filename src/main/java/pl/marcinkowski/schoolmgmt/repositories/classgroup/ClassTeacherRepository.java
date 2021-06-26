package pl.marcinkowski.schoolmgmt.repositories.classgroup;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.marcinkowski.schoolmgmt.entities.lesson.Teacher;

public interface ClassTeacherRepository extends JpaRepository<Teacher, Long> {}
