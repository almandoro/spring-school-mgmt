package pl.marcinkowski.schoolmgmt.api.lesson.service;

import org.springframework.stereotype.Service;
import pl.marcinkowski.schoolmgmt.api.lesson.entity.Teacher;
import pl.marcinkowski.schoolmgmt.api.user.entity.User;
import pl.marcinkowski.schoolmgmt.api.lesson.repository.TeacherRepository;

@Service
public class TeacherService {

    private TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public Teacher getTeacherByUser(User user){
        return teacherRepository.findByUser(user);
    }
}
