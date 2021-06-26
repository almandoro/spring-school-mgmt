package pl.marcinkowski.schoolmgmt.api.lesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.marcinkowski.schoolmgmt.api.lesson.entity.Lesson;
import pl.marcinkowski.schoolmgmt.api.lesson.entity.Teacher;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findByTeacher(Teacher teacher);
}
