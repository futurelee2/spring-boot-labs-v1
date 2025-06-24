package com.example.ch502jwtbasedplainlogin.controller;

import com.example.ch502jwtbasedplainlogin.dto.LoginRequest;
import com.example.ch502jwtbasedplainlogin.dto.LoginResponse;
import com.example.ch502jwtbasedplainlogin.dto.SignUpRequest;
import com.example.ch502jwtbasedplainlogin.dto.UserResponse;
import com.example.ch502jwtbasedplainlogin.service.AuthService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 회원가입

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;
    
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody SignUpRequest signUpRequest) {
        // TODO
         UserResponse userResponse = authService.register(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        // TODO
        // 세션을 통해서 정보를 저장할 수 있음

        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        // TODO
        authService.logout(session);
        return ResponseEntity.ok().build();
    }
} 