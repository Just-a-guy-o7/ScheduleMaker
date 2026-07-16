package com.ScheduleMaker.ScheduleMaker.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/schedule")
public class DefaultController {

    @GetMapping("")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> testMethod() {
        return new ResponseEntity<>("All good",HttpStatus.OK);
    }
    
}
