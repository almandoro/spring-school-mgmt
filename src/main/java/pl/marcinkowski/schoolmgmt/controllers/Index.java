package pl.marcinkowski.schoolmgmt.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Index {

    @GetMapping
    public String index(){
        return "Hey there, check out repo :) -> https://github.com/almandoro/spring-school-mgmt";
    }

}
