package pl.marcinkowski.schoolmgmt.api.lesson.service;

import org.springframework.stereotype.Service;
import pl.marcinkowski.schoolmgmt.api.lesson.entity.ScheduledLesson;
import pl.marcinkowski.schoolmgmt.api.lesson.repository.ScheduledLessonRepository;
import pl.marcinkowski.schoolmgmt.api.schoolclass.entity.SchoolClass;
import pl.marcinkowski.schoolmgmt.api.schoolclass.entity.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ScheduledLessonService {

  private ScheduledLessonRepository scheduledLessonRepository;

  public ScheduledLessonService(ScheduledLessonRepository scheduledLessonRepository) {
    this.scheduledLessonRepository = scheduledLessonRepository;
  }

  public List<ScheduledLesson> getAllStudentLessons(Student student) {
    Collection<SchoolClass> schoolClasses = student.getSchoolClasses();

    List<ScheduledLesson> scheduledLessons = new ArrayList<>();
    for (SchoolClass schoolClass : schoolClasses) {
      schoolClass.setStudents(null);
      List<ScheduledLesson> studentLessons =
          scheduledLessonRepository.getBySchoolClass(schoolClass);

      scheduledLessons.addAll(studentLessons);
    }

    return scheduledLessons;
  }
}
