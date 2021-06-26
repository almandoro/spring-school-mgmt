package pl.marcinkowski.schoolmgmt.api.lesson.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.marcinkowski.schoolmgmt.api.lesson.entity.Lesson;
import pl.marcinkowski.schoolmgmt.api.lesson.entity.ScheduledLesson;
import pl.marcinkowski.schoolmgmt.api.lesson.entity.Teacher;
import pl.marcinkowski.schoolmgmt.api.lesson.service.ScheduledLessonService;
import pl.marcinkowski.schoolmgmt.api.schoolclass.entity.Student;
import pl.marcinkowski.schoolmgmt.api.schoolclass.service.StudentService;
import pl.marcinkowski.schoolmgmt.api.user.entity.User;
import pl.marcinkowski.schoolmgmt.api.lesson.service.LessonService;
import pl.marcinkowski.schoolmgmt.api.lesson.service.TeacherService;
import pl.marcinkowski.schoolmgmt.api.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping("lessons")
public class LessonController {

  private UserService userService;
  private LessonService lessonService;
  private TeacherService teacherService;
  private StudentService studentService;
  private ScheduledLessonService scheduledLessonService;

  public LessonController(
      UserService userService,
      LessonService lessonService,
      TeacherService teacherService,
      StudentService studentService,
      ScheduledLessonService scheduledLessonService) {
    this.userService = userService;
    this.lessonService = lessonService;
    this.teacherService = teacherService;
    this.studentService = studentService;
    this.scheduledLessonService = scheduledLessonService;
  }

  @GetMapping()
  @Secured("{ADMIN}")
  public List<Lesson> getAllLessons() {
    return lessonService.getAll();
  }

  @GetMapping("teacher")
  @Secured("{TEACHER}")
  public List<Lesson> getAllLessonsForTeacher(Authentication user) {
    User foundUser = userService.getAuthUser(user);
    Teacher teacher = teacherService.getTeacherByUser(foundUser);

    return lessonService.getLessonsForTeacher(teacher);
  }

  @GetMapping("student")
  public List<ScheduledLesson> getAllUserLessons(Authentication user) {
    User foundUser = userService.getAuthUser(user);
    Student student = studentService.getStudentByUser(foundUser);
    List<ScheduledLesson> allLessons = scheduledLessonService.getAllStudentLessons(student);

    return allLessons;
  }

  @PutMapping
  public ResponseEntity createLesson(@RequestBody Lesson lesson) {
      return lessonService.updateLesson(lesson);
  }

}
