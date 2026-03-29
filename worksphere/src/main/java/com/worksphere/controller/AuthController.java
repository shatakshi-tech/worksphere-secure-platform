package com.worksphere.controller;

import com.worksphere.entity.User;
import com.worksphere.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // 🔐 REGISTER
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        User savedUser = authService.register(user);

        return ResponseEntity.ok(Map.of(
                "message", "User registered successfully",
                "username", savedUser.getUsername()
        ));
    }

    // 🔑 LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        String token = authService.login(user);

        return ResponseEntity.ok(Map.of(
                "token", token
        ));
    }
}