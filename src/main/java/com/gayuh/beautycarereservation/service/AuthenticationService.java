package com.gayuh.beautycarereservation.service;

import com.gayuh.beautycarereservation.dto.authentication.AuthenticationRequest;
import com.gayuh.beautycarereservation.dto.authentication.AuthenticationResponse;
import com.gayuh.beautycarereservation.dto.register.RegisterRequest;

public interface AuthenticationService {

    public AuthenticationResponse register(RegisterRequest request);

    public AuthenticationResponse authenticate(AuthenticationRequest request);

}
