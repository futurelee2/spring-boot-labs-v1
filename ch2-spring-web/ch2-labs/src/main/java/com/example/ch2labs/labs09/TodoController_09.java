package com.example.ch2labs.labs09;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController_09 {
    private final TodoService_09 service;

    @GetMapping("/{id}")
    // synchronize = sync 동기화
    public ResponseEntity<?> getId(@PathVariable Long id){
        TodoDto response = service.getId(id);
        if (response == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("해당 ID의 할 일이 존재하지 않습니다."));
        }
        return ResponseEntity.ok(response);
    }
}
