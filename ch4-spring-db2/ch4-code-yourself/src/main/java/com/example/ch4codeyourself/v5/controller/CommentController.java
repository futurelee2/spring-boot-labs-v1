package com.example.ch4codeyourself.v5.controller;

import com.example.ch4codeyourself.v5.dto.comment.*;
import com.example.ch4codeyourself.v5.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    // 댓글 CRUD
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentResponse> createComment(@PathVariable Long postId, @RequestBody CommentCreateRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(postId, request));
    }
    
    // 대댓글 포함해서 보여주기
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentPageResponse> getAllComments(@PathVariable Long postId, CommentSearchRequest request) {
        return ResponseEntity.ok(commentService.getCommentsByPost(postId,request));
    }

    @PutMapping("/comments/{commentsId}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable Long commentsId, @RequestBody CommentUpdateRequest request){
        return ResponseEntity.ok(commentService.updateComment(commentsId, request));
    }

    @DeleteMapping("/comment/{commentsId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentsId) {
        commentService.deleteComment(commentsId);
        return ResponseEntity.noContent().build();
    }



}
