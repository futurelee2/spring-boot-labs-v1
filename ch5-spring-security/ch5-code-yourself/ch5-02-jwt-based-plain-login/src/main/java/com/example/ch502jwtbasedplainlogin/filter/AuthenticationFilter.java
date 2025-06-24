//package com.example.ch502jwtbasedplainlogin.filter;
//
//import com.example.ch502jwtbasedplainlogin.config.JwtUtil;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import lombok.RequiredArgsConstructor;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Component // 스프링부트 안에 있기때문에 컴포넌트 등록 필요
//@Order(1)
//@RequiredArgsConstructor
//public class AuthenticationFilter implements Filter {
//
//    private static final String USER_SESSION_KEY = "CURRENT_USER";
//    private static final List<String> PUBLIC_PATHS = Arrays.asList(
//            "/swagger-ui",
//            "/v3/api-docs",
//            "/swagger-resources/**",
//            "/api/auth/login",
//            "/api/auth/register",
//            "/h2-console"
//    );
//
//    private final ObjectMapper objectMapper;
//    private final JwtUtil jwtUtil;
//
//    // 필터는 스프링 영향권 밖이기때문에 자동으로 JSON 변환이 안됨으로 직접 수동으로 해줘야함
//    @Override
//    // 요청받을 때 http 메시지를 톰캣이 파싱해서 request 객체 안에 들어있음 (@RequestBody 처럼)
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        // TODO : 필터 로직 작성
//        // 권한에 따른 인가
//        // 강제 형변환 필요(부모 -> 자식)
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//
//        // request안에 path 정보 들어있음
//        String path = httpRequest.getRequestURI();
//
//        // 로그인하지 않았어도 공개 경로는 접근 가능
//        // 로그인하지 않았을 때 요청한 path가  퍼블릭path이면 인가하겠다.
//        if(isPublicPath(path)){
//            // 필터 성공 시, 다음 스탬으로 넘김 (dispathsevlet ?)
//            chain.doFilter(request, response);
//        }
//        // JWT 토근 방식 인증
//        // 클라이언트 -> 토큰 (정상적 로그인하면 토근 존재)
//
//        String bearearToken = httpRequest.getHeader("Authorization"); // key 값이 Authorization 헤더를 읽으면
//        if(bearearToken != null || bearearToken.startsWith("Bearer ")){ // Bearer 뒤에 토큰 값이 들어감. 규칙임
//            bearearToken = bearearToken.substring(7); // 진짜 토큰만 검색하기 위해서는 "Bearer  " 떼내야함
//        }
//
//    if (bearearToken == null || jwtUtil.validateToken(bearearToken)) {
//        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        httpResponse.setContentType("application/json");
//
//        Map<String, String> errorResponse = new HashMap<>();
//        errorResponse.put("error", "Invalid token");
//        errorResponse.put("status", "error");
//
//        httpResponse.getWriter().write(objectMapper.writeValueAsString(errorResponse));
//        return;
//    }
//
//    chain.doFilter(request, response);
//}
//
//    private boolean isPublicPath(String path) {
//        // 뭐라도 매칭되는게 있으면 True 반환
//        return PUBLIC_PATHS.stream().anyMatch(path::startsWith);
//    }
//}



package com.example.ch502jwtbasedplainlogin.filter;


import com.example.ch502jwtbasedplainlogin.config.JwtUtil;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Order(1)
@RequiredArgsConstructor
public class AuthenticationFilter implements Filter {

    private static final List<String> PUBLIC_PATHS = Arrays.asList(
            "/swagger-ui",
            "/v3/api-docs",
            "/swagger-resources/**",
            "/api/auth/login",
            "/api/auth/register",
            "/h2-console"
    );

    private final ObjectMapper objectMapper;
    private final JwtUtil jwtUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // TODO : 필터 로직 작성

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String path = httpRequest.getRequestURI();

        // 로그인하지 않았을 때도 공개 경로는 가능
        if (isPublicPath(path)) {
            // 다음 스텝
            chain.doFilter(request, response);
            return;
        }

        // JWT 토큰 방식 인증
        // 클라이언트 -> 토큰

        String bearerToken = httpRequest.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            bearerToken = bearerToken.substring(7);
        }

        if (bearerToken == null || !jwtUtil.validateToken(bearerToken)) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.setContentType("application/json");

            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "인증이 필요합니다.");
            errorResponse.put("status", "error");

            httpResponse.getWriter().write(objectMapper.writeValueAsString(errorResponse));
            return;
        }

        chain.doFilter(request, response);
    }



    private boolean isPublicPath(String path) {
        return PUBLIC_PATHS.stream().anyMatch(path::startsWith);
    }
}