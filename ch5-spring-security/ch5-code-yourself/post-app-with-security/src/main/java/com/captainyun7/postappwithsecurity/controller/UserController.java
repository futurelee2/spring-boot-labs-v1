package com.captainyun7.postappwithsecurity.controller;

import com.captainyun7.postappwithsecurity.dto.user.JwtResponse;
import com.captainyun7.postappwithsecurity.dto.user.LoginRequest;
import com.captainyun7.postappwithsecurity.dto.user.SignupRequest;
import com.captainyun7.postappwithsecurity.dto.user.UserResponse;
import com.captainyun7.postappwithsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signup(SignupRequest request) {
       return ResponseEntity.ok(userService.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }


}
