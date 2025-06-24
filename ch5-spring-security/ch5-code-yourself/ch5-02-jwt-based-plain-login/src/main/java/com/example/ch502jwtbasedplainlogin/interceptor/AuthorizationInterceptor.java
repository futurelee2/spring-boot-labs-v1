package com.example.ch502jwtbasedplainlogin.interceptor;

import com.example.ch502jwtbasedplainlogin.config.JwtUtil;
import com.example.ch502jwtbasedplainlogin.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.HashMap;
import java.util.Map;

// 필터로도 인가 구현 가능 (인터셉터도 필터랑 비슷)
// 서블릿이 호출하면 preHandle이 제일 먼저 호출됨?
// 컨트롤러갓다가 응답이 돌아올때  postHandle ? 사용

@Component
@RequiredArgsConstructor
public class AuthorizationInterceptor implements HandlerInterceptor {
    
    private static final String USER_SESSION_KEY = "CURRENT_USER";
    private final ObjectMapper objectMapper;
    private final JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // TODO : 인터셉터 로직 작성

        // 권한 검증
        String path = request.getRequestURI();

        // 관리자 API에 대한 권한 검증 (admin으로 시작하면 검증하겠다)
        if(path.startsWith("/api/admin")){

            String bearerToken = request.getHeader("Authorization");
            if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
                bearerToken = bearerToken.substring(7);
            }

            if (bearerToken == null) {
                sendUnauthorizedResponse(response, "인증이 필요합니다.");
                return false;
            }

            // 토큰은 있지만 유효하지 않다 = 만료됨
            if(!jwtUtil.validateToken(bearerToken)) {
                sendUnauthorizedResponse(response, "유효하지 않는 토큰입니다.");
                return false;
            }

             String role = jwtUtil.getRoleFromToken(bearerToken);

            if(!role.equals("ADMIN")){
                sendUnauthorizedResponse(response, "관리자 권한이 필요합니다.");
                return false;
            }

        }

        // 관리자 권한을 가짐.
        return true;
    }
    
    private void sendUnauthorizedResponse(HttpServletResponse response, String message) throws Exception {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", message);
        errorResponse.put("status", "error");
        
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
    
    private void sendForbiddenResponse(HttpServletResponse response, String message) throws Exception {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", message);
        errorResponse.put("status", "error");
        
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
} 