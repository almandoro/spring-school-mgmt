package pl.marcinkowski.schoolmgmt.api.schoolclass.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.marcinkowski.schoolmgmt.api.schoolclass.entity.SchoolClass;
import pl.marcinkowski.schoolmgmt.api.schoolclass.service.SchoolClassService;
import pl.marcinkowski.schoolmgmt.api.user.entity.User;
import pl.marcinkowski.schoolmgmt.api.user.service.UserService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("groups")
public class SchoolClassController {

    private SchoolClassService schoolClassService;
    private UserService userService;

    public SchoolClassController(SchoolClassService schoolClassService, UserService userService) {
        this.schoolClassService = schoolClassService;
        this.userService = userService;
    }

    @GetMapping
    @Secured({"TEACHER"})
    public Collection<SchoolClass> schoolClass(Authentication user) {
        User authUser = userService.getAuthUser(user);
        return schoolClassService.getStudentClasses(authUser);
    }
}
