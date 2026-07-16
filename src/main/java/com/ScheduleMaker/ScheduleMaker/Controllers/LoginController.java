package com.ScheduleMaker.ScheduleMaker.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ScheduleMaker.ScheduleMaker.Entities.User;
import com.ScheduleMaker.ScheduleMaker.Helpers.JwtGeneraionHelper;
import com.ScheduleMaker.ScheduleMaker.Services.JwtService;
import com.ScheduleMaker.ScheduleMaker.Services.UserServices;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/user")
public class LoginController {

    UserServices userServices;
    PasswordEncoder passwordEncoder;
    JwtGeneraionHelper jwtGeneraionHelper;
    JwtService jwtService;
    @Autowired
    public void setUserServicesAndPasswordEncoder(UserServices userServices,PasswordEncoder passwordEncoder,JwtGeneraionHelper jwtGeneraionHelper,JwtService jwtService){
        this.userServices=userServices;
        this.passwordEncoder=passwordEncoder;
        this.jwtGeneraionHelper=jwtGeneraionHelper;
        this.jwtService=jwtService;
    }
    
    
    @PostMapping("")
    public ResponseEntity<String> addUserToDB(@RequestParam String userId,@RequestParam String password,@RequestParam String name,@RequestParam String role) {
        if(userServices.isUserById(userId)) return new ResponseEntity<>("UserId Already In Use",HttpStatus.BAD_REQUEST);
        User user=new User(userId, passwordEncoder.encode(password), name, role, new ArrayList<>());
        // User user=new User(userId, passwordEncoder.encode(password), role, new ArrayList<>());
        if(userServices.saveUser(user)==true)return new ResponseEntity<>("User saved successfully",HttpStatus.OK);
        else return new ResponseEntity<>("Couldn't save user",HttpStatus.BAD_REQUEST);
    }
    @PutMapping("")
    public ResponseEntity<String> updateUserToDB(@RequestParam String userId,@RequestParam String password, @RequestParam String name,@RequestParam String role) {
        User userInDB=userServices.getUser(userId);
        if(userInDB==null) return new ResponseEntity<>("User with userId = "+userId+" doesn't exist",HttpStatus.BAD_REQUEST);
        if(passwordEncoder.matches(password,userInDB.getPassword())){
            User user=new User(userId, passwordEncoder.encode(password), name,role, new ArrayList<>());
            if(userServices.updateUser(user)==true)return new ResponseEntity<>("User uspdated successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Wrong Password",HttpStatus.NOT_ACCEPTABLE);
    }
    @DeleteMapping("")
    public ResponseEntity<String> deleteUserInDB(@RequestParam String userId,@RequestParam String password){
        User userInDB=userServices.getUser(userId);
        if(userInDB!=null){
            if(passwordEncoder.matches(password, userInDB.getPassword())){
                userServices.deleteUser(userInDB);
                return new ResponseEntity<>("User Deleted",HttpStatus.OK);
            }
            return new ResponseEntity<>("Wrong Password",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("No user with userId = "+userId,HttpStatus.BAD_REQUEST);
    }
    @GetMapping("")
    public ResponseEntity<String> getMethodName(@RequestParam String userId,@RequestParam String password) {
        //Step 1: see if userName and Password are correct
        if(jwtGeneraionHelper.isUsernameAndPasswordValid(userId, password) ){
            //Step 2: generate token for the username
            String jwtToken=jwtService.getJwtTokeString(userId);
            //Step 3 : return the token :)
            return new ResponseEntity<>(jwtToken,HttpStatus.OK);
        }
        return new ResponseEntity<>("Wrong Username or Password",HttpStatus.UNAUTHORIZED);
    }
    
}
