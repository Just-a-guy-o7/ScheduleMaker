package com.ScheduleMaker.ScheduleMaker.Helpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtGeneraionHelper {
    public AuthenticationManager authenticationManager;
    @Autowired
    public void setAuthenticationManager(AuthenticationManager _authenticationManager){
        authenticationManager=_authenticationManager;
    }
    public boolean isUsernameAndPasswordValid(String userName,String password){
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        return authentication.isAuthenticated();
    }

    

}