package com.spring.s3cur1ty.service.impl;

import com.spring.s3cur1ty.configuration.JwtService;
import com.spring.s3cur1ty.dto.request.AuthenticationRequest;
import com.spring.s3cur1ty.dto.request.RegisterRequest;
import com.spring.s3cur1ty.dto.response.AuthenticationResponse;
import com.spring.s3cur1ty.entity.Authority;
import com.spring.s3cur1ty.entity.Role;
import com.spring.s3cur1ty.entity.User;
import com.spring.s3cur1ty.repository.AuthorityRepo;
import com.spring.s3cur1ty.repository.UserRepository;
import com.spring.s3cur1ty.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final AuthorityRepo authorityRepo;
    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        Role role = new Role();
        role.setId(1);
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .role(role)
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
//        List<Authority> authorities = authorityRepo.findAllById();

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
                .token(jwtToken).build();
    }
    public void logout() {

        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
