package com.spring.s3cur1ty.controller;

import com.spring.s3cur1ty.entity.Role;
import com.spring.s3cur1ty.entity.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestController {

//    @PreAuthorize("hasRole('USER')")
    @GetMapping("/test")
    public ResponseEntity<List<User>> test() throws InterruptedException {

        List<String> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        System.out.println(authorities);

        List<User> users = new ArrayList<>();
        if (!authorities.contains("USER"))
            return ResponseEntity.status(403).body(users);
        // Creating 10 user objects
        for (long i = 1; i <= 10; i++) {

//        user = new User(i, "FirstName" + i, "LastName" + i, "user" + i + "@example.com", "token" + i,
//                    "password" + i, new ArrayList<>());
            users.add(User.builder()
                    .id(i)

                            .email("abdellah" + i + "@example.com")
                            .lastName("erray"+i).token((int)i)
                            .role(Role.builder().name("USER").build())
                            .firstName("abdellah"+i)
                    .build());
        }

        // Output the list of users
        for (User user : users) {
            System.out.println(user.toString());
        }
        Thread.sleep(5000);
        System.out.println("test");
        return ResponseEntity.ok(users);
    }
}
