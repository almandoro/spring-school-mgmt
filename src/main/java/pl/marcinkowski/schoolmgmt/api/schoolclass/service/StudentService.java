package pl.marcinkowski.schoolmgmt.api.schoolclass.service;

import org.springframework.stereotype.Service;
import pl.marcinkowski.schoolmgmt.api.schoolclass.entity.Student;
import pl.marcinkowski.schoolmgmt.api.schoolclass.repository.StudentRepository;
import pl.marcinkowski.schoolmgmt.api.user.entity.User;
import pl.marcinkowski.schoolmgmt.api.user.service.UserService;

@Service
public class StudentService {

    private StudentRepository studentService;

    public StudentService(StudentRepository studentService) {
        this.studentService = studentService;
    }

    public Student getStudentByUser(User user){
        Student student = studentService.getByUser(user);

        return student;
    }

}
