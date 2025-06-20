package com.school.student.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

import io.jsonwebtoken.security.Keys;

import java.security.Key;

@Component
public class JwtUtil {

    private static final String SECRET = "MyVeryStrongSecretKey1234567890ABCDEF"; // must match the above

    public String generateToken(String username, List<String> roles) {
        Key key = Keys.hmacShaKeyFor(SECRET.getBytes());
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .claim("roles", roles)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
