package com.ssafy.userservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 모든 요청 경로에 대해
        registry.addMapping("/**")
                // 허용할 도메인/포트
                .allowedOrigins("http://localhost:5173/")
                // 허용할 HTTP 메서드
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                // 자격 증명(쿠키 등) 허용 여부
                .allowCredentials(true)
                // 응답 헤더
                .allowedHeaders("*")
                // Pre-flight 요청(OPTIONS)에 대한 캐싱 시간
                .maxAge(3600);
    }
}

