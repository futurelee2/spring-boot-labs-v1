package com.example.ch4labs.service;

import com.example.ch4labs.domain.Comment;
import com.example.ch4labs.domain.Review;
import com.example.ch4labs.dto.Comment.CommentRequest;
import com.example.ch4labs.dto.Comment.CommentResponse;
import com.example.ch4labs.repository.Comment.CommentRepository;
import com.example.ch4labs.repository.Review.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ReviewRepository reviewRepository;

    public CommentResponse create(CommentRequest request, Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(()-> new EntityNotFoundException("Review not found"));
         Comment comment = Comment.builder()
                 .content(request.getContent())
                 .author(request.getAuthor())
                 .reviewId(reviewId)
                 .createdAt(LocalDateTime.now())
                 .review(review)
                 .build();
         return CommentResponse.from(commentRepository.save(comment));
    }
}
