package com.ScheduleMaker.ScheduleMaker.Config;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ScheduleMaker.ScheduleMaker.Services.JwtService;
import com.ScheduleMaker.ScheduleMaker.ServicesImpl.UserDetailsServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtFilter extends OncePerRequestFilter{

    private JwtService jwtService;
    private ApplicationContext applicationContext;
    @Autowired
    public void setDependencies(JwtService _jwtService,ApplicationContext _applicationContext){
        jwtService=_jwtService;
        applicationContext=_applicationContext;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String authHeader=request.getHeader("Authorization");

        String jwt=null;
        String username=null;

        if(authHeader!=null && authHeader.startsWith("Bearer ")){
            jwt = authHeader.substring(7);
            username=jwtService.getUserNameFromJwt(jwt);
            if( SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails=applicationContext.getBean(UserDetailsServiceImpl.class).loadUserByUsername(username);
                
                if(jwtService.validateToken(jwt,userDetails,username)){
                    
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails, jwt, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

}
