package com.devansh.splitKro.service.security;

import com.devansh.splitKro.dao.request.SignUpRequest;
import com.devansh.splitKro.dao.request.SigninRequest;
import com.devansh.splitKro.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}