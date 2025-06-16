package com.captainyun7.ch2codeyourself._05_validation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice // 전역 예외처리를 담당하는 클래스
public class GlobalExceptionHandler {
    // 예외 발생 시 , 다 처리해줌
    @ExceptionHandler(MethodArgumentNotValidException.class) // SignUpRequest 에서 에러 발생 시 아래 코드 실행
    public ResponseEntity<?> handlerValidation(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
        // 아래 패턴은 외우기
        ex.getBindingResult().getFieldErrors().forEach((error)->{
            errors.put(error.getField(),error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
