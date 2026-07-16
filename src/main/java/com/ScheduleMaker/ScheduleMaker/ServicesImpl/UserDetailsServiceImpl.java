package com.ScheduleMaker.ScheduleMaker.ServicesImpl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ScheduleMaker.ScheduleMaker.Entities.User;
import com.ScheduleMaker.ScheduleMaker.Services.UserServices;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    UserServices userServices;

    @Autowired
    public void setUserServices(UserServices userServices){
        this.userServices=userServices;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userInDB=userServices.getUser(username);
        if(userInDB==null){
            throw new UsernameNotFoundException("User with email = "+username+" does not exist in DB");
        }
        String authority = userInDB.getRole() == null ? "USER" : userInDB.getRole();
        UserDetails userDetails = new UserDetailsImpl(userInDB.getEmail(), userInDB.getPassword(), authority);

        return userDetails;
    }

}
