package com.example.ch4labs.controller;


import com.example.ch4labs.dto.Comment.CommentRequest;
import com.example.ch4labs.dto.Comment.CommentResponse;
import com.example.ch4labs.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService service;
    
    // pk 연결

    @PostMapping("/reviews/{reviewId}/comments")
    public ResponseEntity<CommentResponse> create(@RequestBody CommentRequest commentRequest, @PathVariable Long reviewId) {
        return ResponseEntity.ok(service.create(commentRequest, reviewId));

    }

//    @GetMapping
//    public ResponseEntity<List<CommentResponse>> getAllComments(@RequestParam Integer page, @RequestParam Integer size) {
//    }



}
