package com.example.ch3labs.ch2_labs07_add.exception;


import com.example.ch3labs.ch2_labs07_add.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


//@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TodoNotFoundException.class)
    public ResponseEntity<ErrorResponse> TodoExceptionHandler(TodoNotFoundException e) {
        ErrorResponse error = new ErrorResponse("404", e.getMessage());
        return ResponseEntity.status(404).body(error);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlerAll(Exception e) {
        ErrorResponse error = new ErrorResponse("500", "서버 내부에 오류가 발생하였습니다.");
        return ResponseEntity.status(500).body(error);
    }
}
