package com.ssafy.userservice.service;

import com.ssafy.userservice.entity.User;
import com.ssafy.userservice.repository.UserRepository;
import com.ssafy.userservice.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    // 간단 예시: username, password 받아서 인증
    public String login(String username, String rawPassword) {
        // Step 1. 사용자 조회 -> 디버깅 로그 추가
        System.out.println("[DEBUG] Attempting to login with username: " + username);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    System.out.println("[ERROR] User not found for username: " + username);
                    return new IllegalArgumentException("Invalid username or password");
                });

        System.out.println("[DEBUG] User found: " + user.getUsername() + " " + user.getPassword());

        // Step 2. 비밀번호 검사 -> 디버깅 로그 추가
        if (!rawPassword.equals(user.getPassword())) {
            System.out.println("rawPassword"+" "+rawPassword+" "+user.getPassword());
            System.out.println("[ERROR] Password mismatch for username: " + username);
            throw new IllegalArgumentException("Invalid username or password");
        }

        // Step 3. 토큰 발급
        System.out.println("[DEBUG] Login successful. Issuing token for username: " + username);
        return jwtTokenProvider.createToken(user.getUsername(), "ROLE_USER");
    }
}
