package com.school.student.controllers;

import com.school.student.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/public")
    public String publicApi() {
        return "This is a public endpoint.";
    }

    @GetMapping("/user")
    public String userApi() {
        return "This is a USER endpoint.";
    }

    @GetMapping("/admin")
    public String adminApi(Authentication authentication) {
        System.out.println("Username: " + authentication.getName());
        System.out.println("Authorities: " + authentication.getAuthorities());
        return "Admin area: " + authentication.getName();
    }


    @GetMapping("/token")
    public ResponseEntity<?> getToken(@RequestParam String username, @RequestParam String role) {
        try {
            System.out.println(" Received username=" + username + ", role=" + role);
            String token = jwtUtil.generateToken(username, List.of(role.toUpperCase()));
            return ResponseEntity.ok("Bearer " + token);
        } catch (Exception e) {
            e.printStackTrace(); // Log actual issue
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

}
