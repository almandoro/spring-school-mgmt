package pl.marcinkowski.schoolmgmt.repositories.lesson;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.marcinkowski.schoolmgmt.entities.lesson.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
