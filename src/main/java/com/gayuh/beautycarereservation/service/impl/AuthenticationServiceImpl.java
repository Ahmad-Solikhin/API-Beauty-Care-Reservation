package com.gayuh.beautycarereservation.service.impl;

import com.gayuh.beautycarereservation.domain.Role;
import com.gayuh.beautycarereservation.domain.UserAccount;
import com.gayuh.beautycarereservation.dto.authentication.AuthenticationRequest;
import com.gayuh.beautycarereservation.dto.authentication.AuthenticationResponse;
import com.gayuh.beautycarereservation.dto.register.RegisterRequest;
import com.gayuh.beautycarereservation.repository.UserAccountRepository;
import com.gayuh.beautycarereservation.service.AuthenticationService;
import com.gayuh.beautycarereservation.service.GenderService;
import com.gayuh.beautycarereservation.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserAccountRepository userAccountRepository;
    private final GenderService genderService;

    private final AuthenticationManager authenticationManager;
    @Override
    public AuthenticationResponse register(RegisterRequest request) {

        UserAccount userAccount = new UserAccount();
        userAccount.setAge(request.getAge());
        userAccount.setEmail(request.getEmail());
        userAccount.setName(request.getName());
        userAccount.setPassword(passwordEncoder.encode(request.getPassword()));
        userAccount.setGender(genderService.findById(request.getIdGender()));
        userAccount.setBirthDate(request.getBirthDate());
        userAccount.setRole(Role.USER);

        userAccountRepository.save(userAccount);

        String token = jwtService.generatedToken(userAccount);

        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()
                ));

        UserAccount userAccount = userAccountRepository.findByEmail(request.getEmail())
                .orElseThrow();

        String token = jwtService.generatedToken(userAccount);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }
}
