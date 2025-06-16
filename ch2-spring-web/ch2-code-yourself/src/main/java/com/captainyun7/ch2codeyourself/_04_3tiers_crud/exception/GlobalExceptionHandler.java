package com.captainyun7.ch2codeyourself._04_3tiers_crud.exception;

import com.captainyun7.ch2codeyourself._04_3tiers_crud.dto.ErrorResponsse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@ControllerAdvice
public class GlobalExceptionHandler {
//    @ExceptionHandler(RuntimeException.class) //부모임으로 부모가 자식을 잡을 수 있음.
//    public ResponseEntity<String> runtimeException(RuntimeException ex) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//    }

//    @ExceptionHandler(PostNotFoundException.class) // 위 코드 변경
//    public ResponseEntity<String> PostNotFoundException(PostNotFoundException ex) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage()); // 텍스트로 주지말고 객체로 줘야 json으로 값 노출하는게 좋음
//    }

    // @ExceptionHandler(RuntimeException.class) 로 바꾸게 되면 RuntimeException error 가 터졌을때 아래 코드 실행됨
    // -> PostNotFoundException 이 실행됨
    @ExceptionHandler(PostNotFoundException.class) // 위 코드 변경
    public ResponseEntity<ErrorResponsse> postNotFoundException(PostNotFoundException ex) {
        ErrorResponsse errorResponsse = new ErrorResponsse(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponsse);
        }
}
