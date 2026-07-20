package com.ScheduleMaker.ScheduleMaker.Helpers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.ScheduleMaker.ScheduleMaker.Entities.User;
import com.ScheduleMaker.ScheduleMaker.Services.UserServices;


@Component
public class GetCurrentUser {

    private UserServices userServices;

    public GetCurrentUser(UserServices userServices){
        this.userServices=userServices;
    }

    public User loggedInUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email="";
        
        email=authentication.getName().toString();
        

        
        return userServices.getUser(email);
    }
}
