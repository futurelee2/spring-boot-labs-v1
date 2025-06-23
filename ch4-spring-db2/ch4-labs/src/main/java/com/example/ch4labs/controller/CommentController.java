package com.example.ch4labs.controller;

import com.example.ch4labs.dto.Comment.*;
import com.example.ch4labs.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/reviews/{reviewId}/comments")
    public ResponseEntity<CommentResponse> createComment(@PathVariable Long reviewId, @RequestBody CommentCreateRequest request) {
        return ResponseEntity.ok(commentService.createComment(reviewId, request));
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable Integer commentId, @RequestBody CommentUpdateRequest request) {
        return ResponseEntity.ok(commentService.updateComment(commentId,request));
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer commentId) {
        commentService.delete(commentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<CommentPageResponse> getAllComment(@PathVariable Integer reviewId, CommentSearchRequest request) {
        return ResponseEntity.ok(commentService.getAllComment(reviewId, request));
    }
}
