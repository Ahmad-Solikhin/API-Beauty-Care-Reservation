package com.gayuh.beautycarereservation.controller;

import com.gayuh.beautycarereservation.dto.authentication.AuthenticationRequest;
import com.gayuh.beautycarereservation.dto.authentication.AuthenticationResponse;
import com.gayuh.beautycarereservation.dto.register.RegisterRequest;
import com.gayuh.beautycarereservation.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/v1/auth/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/v1/auth/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
