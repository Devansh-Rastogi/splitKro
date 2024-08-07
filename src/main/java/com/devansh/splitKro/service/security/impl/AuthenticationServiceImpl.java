package com.devansh.splitKro.service.security.impl;

import com.devansh.splitKro.request.SignUpRequest;
import com.devansh.splitKro.request.SigninRequest;
import com.devansh.splitKro.response.JwtAuthenticationResponse;
import com.devansh.splitKro.model.user.User;
import com.devansh.splitKro.repository.UserRepository;
import com.devansh.splitKro.service.security.AuthenticationService;
import com.devansh.splitKro.service.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        User user = User.builder().name(request.getFirstName())
                .emailId(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .mobileNumber(request.getMobileNumber()).build();
        userRepository.save(user);
        String jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userRepository.findByEmailId(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        String jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
