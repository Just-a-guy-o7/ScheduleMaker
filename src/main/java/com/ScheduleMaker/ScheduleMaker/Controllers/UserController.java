package com.ScheduleMaker.ScheduleMaker.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/User")
public class UserController {
    @GetMapping("")
    public String login(@RequestParam String param) {
        return new String();
    }
    @PostMapping("")
    public String signUp(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }
    
}
