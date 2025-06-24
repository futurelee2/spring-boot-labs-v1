package com.captainyun7.postappwithsecurity.service;

import com.captainyun7.postappwithsecurity.domain.RefreshToken;
import com.captainyun7.postappwithsecurity.domain.User;
import com.captainyun7.postappwithsecurity.dto.user.JwtResponse;
import com.captainyun7.postappwithsecurity.dto.user.LoginRequest;
import com.captainyun7.postappwithsecurity.dto.user.SignupRequest;
import com.captainyun7.postappwithsecurity.dto.user.UserResponse;
import com.captainyun7.postappwithsecurity.repository.user.UserRepository;
import com.captainyun7.postappwithsecurity.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;

    public UserResponse signup(SignupRequest request) {
        // 사용자 이름 중복 검사 
        if (userRepository.existsByUsername(request.getUsername())){
             throw new RuntimeException("이미 사용중인 사용자 이름입니다.");
         }
        
        // 비밀번호 암호화
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        return createUser(request);
    }

    

    public JwtResponse login(LoginRequest request) {
        // 사용자 인증
        // - 토큰 생성 (아이디, 비밀번호)
        // 인증 성공 시 인증 정보 저장
        // 인증된 정보 기반으로 access 토큰과 refresh 토큰 생성
        // 생성된 토큰을 DTO에 담아 반환
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String accessToken = jwtUtil.generateToken(userDetails);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getUsername());
        return new JwtResponse(accessToken,refreshToken);
    
    }


    private UserResponse createUser(SignupRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .role(request.getRole())
                .build();
        userRepository.save(user);

        return UserResponse.from(user);


    }

}
