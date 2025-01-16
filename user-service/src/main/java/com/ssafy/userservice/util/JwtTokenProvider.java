package com.ssafy.userservice.util;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${jwt.secretKey:mySecretKey}")
    private String secretKey;

    @Value("${jwt.expirationMs:3600000}")
    private long expirationMs;

    // 토큰 생성
    public String createToken(String username, String role) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("role", role);

        System.out.println("secreteKey : "+secretKey);

        Date now = new Date();
        Date validity = new Date(now.getTime() + expirationMs);

        // HS256 방식
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // 토큰 유효성 검증
    public boolean validateToken(String token) {
        try {
            Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // 토큰에서 username 추출
    public String getUsername(String token) {
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
        return Jwts.parserBuilder()
                .setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Authentication 객체 생성
    public Authentication getAuthentication(String username) {
        // 이 예시에선 간단히 ROLE_USER만 부여
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
        User principal = new User(username, "", Collections.singleton(authority));
        return new UsernamePasswordAuthenticationToken(principal, "", principal.getAuthorities());
    }
}

