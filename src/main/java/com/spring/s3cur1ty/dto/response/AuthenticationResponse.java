package com.spring.s3cur1ty.dto.response;


import com.spring.s3cur1ty.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String token;

//    @Enumerated(EnumType.STRING)
    private Role role;
}
