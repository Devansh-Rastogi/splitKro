package com.devansh.splitKro.service.security;

import com.devansh.splitKro.request.SignUpRequest;
import com.devansh.splitKro.request.SigninRequest;
import com.devansh.splitKro.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}