package pl.marcinkowski.schoolmgmt.api.schoolclass.service;

import org.springframework.stereotype.Service;
import pl.marcinkowski.schoolmgmt.api.schoolclass.entity.SchoolClass;
import pl.marcinkowski.schoolmgmt.api.schoolclass.entity.Student;
import pl.marcinkowski.schoolmgmt.api.schoolclass.repository.SchoolClassRepository;
import pl.marcinkowski.schoolmgmt.api.schoolclass.repository.StudentRepository;
import pl.marcinkowski.schoolmgmt.api.user.entity.User;

import java.util.Collection;

@Service
public class SchoolClassService {

  private SchoolClassRepository schoolClassRepository;
  private StudentRepository studentRepository;

  public SchoolClassService(SchoolClassRepository schoolClassRepository, StudentRepository studentRepository) {
    this.schoolClassRepository = schoolClassRepository;
    this.studentRepository = studentRepository;
  }

  public Collection<SchoolClass> getStudentClasses(User user) {
    Student student = studentRepository.getByUser(user);
    Collection<SchoolClass> schoolClasses = student.getSchoolClasses();
    schoolClasses.forEach(sC -> sC.setStudents(null));
    return schoolClasses;
  }
}
