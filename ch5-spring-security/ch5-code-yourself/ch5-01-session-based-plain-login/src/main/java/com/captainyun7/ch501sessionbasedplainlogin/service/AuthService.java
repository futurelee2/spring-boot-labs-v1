package com.captainyun7.ch501sessionbasedplainlogin.service;

import com.captainyun7.ch501sessionbasedplainlogin.dto.LoginRequest;
import com.captainyun7.ch501sessionbasedplainlogin.dto.SignUpRequest;
import com.captainyun7.ch501sessionbasedplainlogin.dto.UserResponse;
import com.captainyun7.ch501sessionbasedplainlogin.domain.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UserService userService;
    private static final String USER_SESSION_KEY = "CURRENT_USER"; // 상수 선언

    public UserResponse register(SignUpRequest signUpRequest) {
        // TODO
        // (로직 먼저 생각하기)
        // 1. 이미 있는 유저인지 검증 (중복확인) + 이메일 검증 (중복확인)
        // 2. 없으면 저장. 있으면 회원가입 X

        if(userService.existsByUsername(signUpRequest.getUsername())) {
            throw new RuntimeException("이미 사용 중인 사용자 이름 입니다.");
        }

        if(userService.existsByEmail(signUpRequest.getEmail())) {
            throw new RuntimeException("이미 사용 중인 이메일 입니다.");
        }

        // 검증 통과 시 생성
        return userService.createUser(signUpRequest);
    }

    public UserResponse login(LoginRequest loginRequest, HttpSession session) {
        // TODO
        // 사용자가 있는지 확인 by username
        // 있으면 비밀번호 검증
        // 비밀번호 통과하면 세션에 사용자 정보 저장
        User user = userService.getUserByUsername(loginRequest.getUsername()).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
        
        // 세션에 저장
        session.setAttribute(USER_SESSION_KEY, user);

        return UserResponse.fromEntity(user);
    }

    public void logout(HttpSession session) {
        // TODO
        // 세션에서 사용자 정보 지우기
        session.removeAttribute(USER_SESSION_KEY);
        session.invalidate(); // 무효화 (초기화)

    }

    public User getCurrentUser(HttpSession session) {
        return (User) session.getAttribute(USER_SESSION_KEY);
    }
} 