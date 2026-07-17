package com.ScheduleMaker.ScheduleMaker.ServicesImpl;

import com.ScheduleMaker.ScheduleMaker.Services.JwtService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements JwtService{

    @Value("${JWT_SECRET}")
    private String secretKey;

    private SecretKey getKey(){
        byte[] keyValue=Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyValue);
    }

    @Override
    public String getJwtTokeString(String userName) {
        Map<String,Objects> claims=new HashMap<>();
        String jwtToken=Jwts.builder()
                        .claims()
                        .add(claims)
                        .subject(userName)
                        .issuedAt(new Date(System.currentTimeMillis()))
                        .expiration(new Date(System.currentTimeMillis()+(60*60*300)))
                        .and()
                        .signWith(getKey())
                        .compact();
        return jwtToken;
            
    }


    @Override
    public String getUserNameFromJwt(String jwt) {
        return extractClaim(jwt,Claims::getSubject);
    }

    private <T> T extractClaim(String jwt,Function<Claims, T> claimResolver){
        final Claims claims= extractAllClaims(jwt);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String jwt){
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }


    @Override
    public boolean validateToken(String jwt,UserDetails userDetails,String username) {
        
        if(username!=null && userDetails.getUsername().equals(username) && !isTokenExpired(jwt))return true;
        return false;
    }

    private boolean isTokenExpired(String jwt){
        return extractExpiration(jwt).before(new Date());
    }
    private Date extractExpiration(String jwt){
        return extractClaim(jwt, Claims::getExpiration);
    }
}
