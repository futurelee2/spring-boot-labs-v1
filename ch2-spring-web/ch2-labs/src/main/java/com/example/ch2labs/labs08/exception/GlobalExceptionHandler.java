package com.example.ch2labs.labs08.exception;

import com.example.ch2labs.labs08.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

//@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException e){
        Map<String, String> errors = new HashMap<>();
        // 내가 작성한 오류 코드
//        errors.put("times", e.getMessage());
//        ErrorResponse response = new ErrorResponse(errors);
//        System.out.println(e.getMessage());
//        return ResponseEntity.status(404).body(response);

        // 처음에 getFiled 는 필드 유효성 검사일때, all 은 클래스
        e.getBindingResult().getAllErrors().forEach(error-> {
            errors.put("times", error.getDefaultMessage());
        });

        ErrorResponse response = new ErrorResponse(errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

    }
}

