package pl.marcinkowski.schoolmgmt.api.lesson.service;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.marcinkowski.schoolmgmt.api.lesson.entity.Lesson;
import pl.marcinkowski.schoolmgmt.api.lesson.entity.ScheduledLesson;
import pl.marcinkowski.schoolmgmt.api.lesson.entity.Teacher;
import pl.marcinkowski.schoolmgmt.api.lesson.repository.LessonRepository;
import pl.marcinkowski.schoolmgmt.api.schoolclass.entity.Student;

import java.util.List;
import java.util.Optional;

@Service
public class LessonService {

    private LessonRepository lessonRepository;

    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public List<Lesson> getLessonsForTeacher(Teacher teacher) {
        return lessonRepository.findByTeacher(teacher);
    }

    public List<Lesson> getAll() {
        return lessonRepository.findAll();
    }

}
