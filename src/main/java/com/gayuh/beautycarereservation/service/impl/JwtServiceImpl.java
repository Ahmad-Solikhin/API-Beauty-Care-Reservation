package com.gayuh.beautycarereservation.service.impl;

import com.gayuh.beautycarereservation.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {

    private static final String SECRET_KEY = "f5876r385f37623f578916i5197f97tsg9ot7li24unjvadilaug3i7213874255254237";

    @Override
    public String extractUserEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);

        //Cari tau fungsi dari Dunction itu apa
        //Owhh jadi ini isis pertamanya nanti jadi functionnya, trus yang kedua itu argumennya atau parameternya apa
        return claimsResolver.apply(claims);
    }

    @Override
    public String generatedToken(Map<String, Object> extractClaims, UserDetails userDetails) {
        ZonedDateTime issuedAt = ZonedDateTime.now(ZoneId.of("Asia/Jakarta"));

        return Jwts
                .builder()
                .setClaims(extractClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(Date.from(issuedAt.toInstant()))
                .setExpiration(Date.from(issuedAt.plusHours(24).toInstant()))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    //Ini generated token yang cuma berisi userDetails saja
    @Override
    public String generatedToken(UserDetails userDetails) {
        return generatedToken(new HashMap<>(), userDetails);
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userEmail = extractUserEmail(token);
        return (userEmail.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    @Override
    public boolean isTokenExpired(String token) {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Jakarta"));
        return extractExpiration(token).before(Date.from(now.toInstant()));
    }

    @Override
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //Method buat decode token jwt
    private Key getSigninKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
