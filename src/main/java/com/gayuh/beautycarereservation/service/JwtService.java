package com.gayuh.beautycarereservation.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public interface JwtService {

    public String extractUserEmail(String token);

    public Claims extractAllClaims(String token);

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    public String generatedToken(Map<String, Object> extractClaims, UserDetails userDetails);

    public String generatedToken(UserDetails userDetails);

    public boolean isTokenValid(String token, UserDetails userDetails);

    public boolean isTokenExpired(String token);

    public Date extractExpiration(String token);
}
