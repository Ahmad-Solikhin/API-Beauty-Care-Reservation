package com.gayuh.beautycarereservation.config;


import com.gayuh.beautycarereservation.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    //Ini udah bawaan dari spring, tapi bisa diimplementasi untuk membuat userDetails sendiri menggunakan bean di AppConfig
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String keyBearer = "Bearer ";
        final String jwt;
        final String userEmail;

        //Ngecek apakah authHeadernya null atau ga diawali dengan bearer maka akan langsung berenti
        if (authHeader == null || !authHeader.startsWith(keyBearer)){
            filterChain.doFilter(request, response);
            return;
        }

        //Ngambil jwt nya setelah "Bearer "
        jwt = authHeader.substring(keyBearer.length());
        userEmail = jwtService.extractUserEmail(jwt); // extract user email nya dari jwt token yang di dapat

        //Sebagai catatan juga, ini kalo diubah tokennya maka harusnya userEmail nya gabakal ada karena signaturenya berubah juga

        //Ini handler apakah user udah authenticated atau belum, kalo belum ini jalan
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(jwt, userDetails)){

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
