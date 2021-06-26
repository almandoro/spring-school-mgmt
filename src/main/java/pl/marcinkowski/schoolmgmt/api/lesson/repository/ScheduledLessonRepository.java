package pl.marcinkowski.schoolmgmt.api.lesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.marcinkowski.schoolmgmt.api.lesson.entity.ScheduledLesson;
import pl.marcinkowski.schoolmgmt.api.schoolclass.entity.SchoolClass;

import java.util.List;

public interface ScheduledLessonRepository extends JpaRepository<ScheduledLesson, Long> {

    List<ScheduledLesson> getBySchoolClass(SchoolClass schoolClass);
}
