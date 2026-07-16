package com.ScheduleMaker.ScheduleMaker.ServicesImpl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class UserDetailsImpl implements UserDetails{

    private String userId;
    private String password;
    private List<GrantedAuthority> roles;

    public UserDetailsImpl(String _userId,String _password,String authority){
        userId=_userId;
        password=_password;
        String normalizedAuthority = authority == null ? "USER" : authority;
        roles = Collections.singletonList(new SimpleGrantedAuthority(normalizedAuthority));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userId;
    }

}
