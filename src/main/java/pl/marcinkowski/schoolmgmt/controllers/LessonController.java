package pl.marcinkowski.schoolmgmt.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("lessons")
public class LessonController {

    @GetMapping("/")
    public String getAllLessons(){
        return "all lessons";
    }
}
