package com.example.ch502jwtbasedplainlogin.service;


import com.example.ch502jwtbasedplainlogin.config.JwtUtil;
import com.example.ch502jwtbasedplainlogin.dto.LoginRequest;
import com.example.ch502jwtbasedplainlogin.dto.LoginResponse;
import com.example.ch502jwtbasedplainlogin.dto.SignUpRequest;
import com.example.ch502jwtbasedplainlogin.dto.UserResponse;
import com.example.ch502jwtbasedplainlogin.domain.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UserService userService;
    // 기존 세션방식에서 필요함
    private static final String USER_SESSION_KEY = "CURRENT_USER"; // 상수 선언
    private final JwtUtil jwtUtil;


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

    public LoginResponse login(LoginRequest loginRequest) {
        // TODO
        // 사용자가 있는지 확인 by username
        // 있으면 비밀번호 검증
        // 비밀번호 통과하면 세션에 사용자 정보 저장
        User user = userService.getUserByUsername(loginRequest.getUsername()).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
        
        // 기존 세션 방식
        // "쿠키" 로 클라이언트에게 전달
        // session.setAttribute(USER_SESSION_KEY, user);
        

        // JWT 방식으로 전환
        // 토큰을 발행 (서버에서 로그인 성공 시, 토큰 발행 후 클라이언트에게 전달)
        String token = jwtUtil.generateToken(user);

        return LoginResponse.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .token(token)
                .build();
    }

    public void logout(HttpSession session) {
        // TODO
        // 기존 세션 방식
//        session.removeAttribute(USER_SESSION_KEY);
//        session.invalidate(); // 무효화 (초기화)

        // 토큰은 세션처럼 로그아웃하는게 애매함


    }

    public User getCurrentUser(HttpSession session) {
        return (User) session.getAttribute(USER_SESSION_KEY);
    }
} 