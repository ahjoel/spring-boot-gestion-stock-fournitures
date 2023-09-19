package com.gestock.fourniture.service.security;

import com.gestock.fourniture.model.dto.request.SignUpRequest;
import com.gestock.fourniture.model.dto.request.SigninRequest;
import com.gestock.fourniture.model.dto.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}