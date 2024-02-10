package com.spring.s3cur1ty.controller;

import com.spring.s3cur1ty.dto.request.AuthenticationRequest;
import com.spring.s3cur1ty.dto.request.RegisterRequest;
import com.spring.s3cur1ty.dto.response.AuthenticationResponse;
import com.spring.s3cur1ty.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        System.out.println(request);
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        authenticationService.logout(); // Implement logout logic in the service
        return ResponseEntity.ok("Logged out successfully");
    }
}
