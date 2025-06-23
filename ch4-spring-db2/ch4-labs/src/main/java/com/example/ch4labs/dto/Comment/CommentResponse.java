package com.example.ch4labs.dto.Comment;

import com.example.ch4labs.domain.Comment;
import com.example.ch4labs.domain.Review;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {
    private Long id;
    private String content;
    private String author;
    private Long reviewId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Review review;

    public static CommentResponse from(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getAuthor(),
                comment.getReviewId(),
                comment.getCreatedAt(),
                comment.getUpdatedAt(),
                comment.getReview()
        );
    }
}
