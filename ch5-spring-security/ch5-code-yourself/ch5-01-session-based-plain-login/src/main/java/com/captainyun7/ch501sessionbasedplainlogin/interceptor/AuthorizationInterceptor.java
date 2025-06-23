package com.captainyun7.ch501sessionbasedplainlogin.interceptor;

import com.captainyun7.ch501sessionbasedplainlogin.domain.User;
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
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // TODO : 인터셉터 로직 작성

        // 권한 검증
        String path = request.getRequestURI();

        // 관리자 API에 대한 권한 검증 (admin으로 시작하면 검증하겠다)
        if(path.startsWith("/api/admin")){
            HttpSession session = request.getSession();

            // 필터에서 검증을 하고 또 검증하게 됨( 이중검증)
            if(session == null){
                sendUnauthorizedResponse(response, "인증이 필요합니다.");
                return false; // false 로 반환되면 컨트롤러 (다음단계)로 못감
            }

             User user = (User)session.getAttribute(USER_SESSION_KEY);

            if(user == null){
                sendUnauthorizedResponse(response, "인증이 필요합니다.");
                return false; // false 로 반환되면 컨트롤러 (다음단계)로 못감
            }

            if(!user.getRole().equals(("ADMIN"))){
                sendForbiddenResponse(response, "관리자 권한이 필요합니다.");
                return false;
            };
        }

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