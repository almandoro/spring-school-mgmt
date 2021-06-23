package pl.marcinkowski.schoolmgmt.repositories.classgroup;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.marcinkowski.schoolmgmt.entities.classgroup.ClassTeacher;

public interface ClassTeacherRepository extends JpaRepository<ClassTeacher, Long> {}
