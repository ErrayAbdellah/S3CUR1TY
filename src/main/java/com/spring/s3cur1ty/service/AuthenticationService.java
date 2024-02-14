package com.spring.s3cur1ty.service;

import com.spring.s3cur1ty.dto.request.AuthenticationRequest;
import com.spring.s3cur1ty.dto.request.RegisterRequest;
import com.spring.s3cur1ty.dto.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest user);

    AuthenticationResponse authenticate(AuthenticationRequest user);
    void logout();
}
