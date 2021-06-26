package pl.marcinkowski.schoolmgmt.api.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.marcinkowski.schoolmgmt.api.user.entity.User;
import pl.marcinkowski.schoolmgmt.api.user.service.UserService;
import pl.marcinkowski.schoolmgmt.utils.ErrorMessage;
import pl.marcinkowski.schoolmgmt.utils.ErrorResponse;

import javax.persistence.EntityExistsException;

@RestController
@RequestMapping("user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ExceptionHandler({})
    public User getLoggedUserDetails(Authentication user){
       return userService.getAuthUser(user);

    }
}
