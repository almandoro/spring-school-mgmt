package pl.marcinkowski.schoolmgmt.api.lesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.marcinkowski.schoolmgmt.api.lesson.entity.Teacher;
import pl.marcinkowski.schoolmgmt.api.user.entity.User;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Teacher findByUser(User user);
}
