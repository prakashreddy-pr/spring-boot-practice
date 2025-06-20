package com.school.student.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@Configuration
public class JwtDecoderConfig {
    @Bean
    public JwtDecoder jwtDecoder() {
        String secret = "my-very-secret-key";
        return NimbusJwtDecoder.withSecretKey(
            new javax.crypto.spec.SecretKeySpec(secret.getBytes(), "HmacSHA256")
        ).build();
    }
}
