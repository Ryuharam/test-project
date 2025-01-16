package com.ssafy.userservice.controller;

import com.ssafy.userservice.dto.LoginRequest;
import com.ssafy.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        String username = request.getUsername();
        String password = request.getPassword();

        System.out.println(username + " " + password);

        String token = userService.login(username, password);
        return ResponseEntity.ok(token);
    }
}
