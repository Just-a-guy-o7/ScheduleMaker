package com.ScheduleMaker.ScheduleMaker.Services;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    public String getJwtTokeString(String userName);

    public String getUserNameFromJwt(String jwt);

    public boolean validateToken(String jwt,UserDetails userDetails,String username);
}
