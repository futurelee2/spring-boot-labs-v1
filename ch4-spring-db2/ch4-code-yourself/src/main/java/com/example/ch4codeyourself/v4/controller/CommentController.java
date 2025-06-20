package com.example.ch4codeyourself.v4.controller;

import com.example.ch4codeyourself.v4.domain.Comment;
import com.example.ch4codeyourself.v4.domain.Post;
import com.example.ch4codeyourself.v4.dto.comment.CommentCreateRequest;
import com.example.ch4codeyourself.v4.dto.comment.CommentPageResponse;
import com.example.ch4codeyourself.v4.dto.comment.CommentResponse;
import com.example.ch4codeyourself.v4.dto.comment.CommentUpdateRequest;
import com.example.ch4codeyourself.v4.repository.PostRepository;
import com.example.ch4codeyourself.v4.service.CommentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentPageResponse> getAllComments(@PathVariable Long postId,
                                                                    @RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(commentService.getCommentsByPost(postId,page,size));
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
