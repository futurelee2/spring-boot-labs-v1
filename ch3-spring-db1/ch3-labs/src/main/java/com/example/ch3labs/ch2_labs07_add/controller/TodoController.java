package com.example.ch3labs.ch2_labs07_add.controller;


import com.example.ch3labs.ch2_labs07_add.dto.TodoCreateRequest;
import com.example.ch3labs.ch2_labs07_add.dto.TodoResponse;
import com.example.ch3labs.ch2_labs07_add.dto.TodoUpdateRequest;
import com.example.ch3labs.ch2_labs07_add.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;


    // R
    @GetMapping("/{id}")
    public ResponseEntity<TodoResponse> getTodo(@PathVariable Long id){
        return ResponseEntity.ok(todoService.getTodo(id));
    }

    //C
    @PostMapping
    public ResponseEntity<TodoResponse> createTodo(@RequestBody TodoCreateRequest request){
                                                                // request -> 서비스에서 todo 바꾸고 저장 -> todo를 todoResponse 바꾸고 반환 -> 서비스에서 받은 todoResponse 컨트롤러에서 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.createTodo(request));
    }

    //D
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }

    //U
    @PutMapping("/{id}")
    public ResponseEntity<TodoResponse> updateTodo(@PathVariable Long id, @RequestBody TodoUpdateRequest request){
        return ResponseEntity.ok(todoService.updateTodo(id, request));
    }
}
