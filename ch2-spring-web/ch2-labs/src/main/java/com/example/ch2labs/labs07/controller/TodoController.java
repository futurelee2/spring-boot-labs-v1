package com.example.ch2labs.labs07.controller;

import com.example.ch2labs.labs07.domain.Todo;
import com.example.ch2labs.labs07.dto.TodoCreateRequest;
import com.example.ch2labs.labs07.dto.TodoResponse;
import com.example.ch2labs.labs07.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;


    @GetMapping("/{id}")
    public ResponseEntity<TodoResponse> getTodo(@PathVariable Long id){
        return ResponseEntity.ok(todoService.getTodo(id));
    }

    @PostMapping
    public ResponseEntity<TodoResponse> createTodo(@RequestBody TodoCreateRequest request){
                                                                // request -> 서비스에서 todo 바꾸고 저장 -> todo를 todoResponse 바꾸고 반환 -> 서비스에서 받은 todoResponse 컨트롤러에서 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.createTodo(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();

    }

}
