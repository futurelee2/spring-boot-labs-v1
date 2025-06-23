package com.example.ch4labs.service;

import com.example.ch4labs.domain.Comment;
import com.example.ch4labs.domain.Review;
import com.example.ch4labs.dto.Comment.*;
import com.example.ch4labs.repository.Comment.CommentRepository;
import com.example.ch4labs.repository.Review.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final ReviewRepository reviewRepository;

    public CommentResponse createComment(Long reviewId, CommentCreateRequest request) {
        Review requsetReview = reviewRepository.findById(reviewId).orElseThrow(()-> new EntityNotFoundException("해당 게시글이 없습니다."));

        Comment comment = Comment.builder()
                .content(request.getContent())
                .author(request.getAuthor())
                .createdAt(LocalDateTime.now())
                .review(requsetReview)
                .build();

        if(request.getParentCommentId() != null){
            Comment parent = commentRepository.findById(request.getParentCommentId()).orElseThrow(()-> new EntityNotFoundException("해당 부모 댓글이 없습니다."));
            comment.setParentComment(parent);
        }

        commentRepository.save(comment);

        return CommentResponse.from(comment);
    }

    public CommentResponse updateComment(Integer commentId, CommentUpdateRequest request) {
         Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new EntityNotFoundException("해당 댓글을 찾을 수 없습니다."));
         comment.setContent(request.getContent());
         comment.setUpdatedAt(LocalDateTime.now());
         return CommentResponse.from(comment);
    };

    public void delete(Integer commentId) {
        commentRepository.deleteById(commentId);
    }

    public CommentPageResponse getAllComment(Integer reviewId, CommentSearchRequest request) {

        Pageable pageable = PageRequest.of(request.getCommentPage(), request.getCommentSize());

        if (request.isHierarchical()){
            return hierachicalByComment(reviewId, pageable);
        }else{
            return flatByComment(reviewId, pageable);
        }


    }

    private CommentPageResponse flatByComment(Integer reviewId, Pageable pageable) {
        // 해당 게시글의 댓글들 모두 가져오기
        Page<CommentResponse> comments = commentRepository.findByReviewId(reviewId,pageable).map(CommentResponse::from);
        return CommentPageResponse.from(comments);

    }

    private CommentPageResponse hierachicalByComment(Integer reviewId, Pageable pageable) {
       Page<CommentResponse> parentResponse = commentRepository.findByReviewIdAndParentCommentIsNull(reviewId, pageable).map(CommentResponse::from);


       List<CommentResponse> completeResponse = new ArrayList<>();
       for (CommentResponse parent : parentResponse.getContent()) {
            List<CommentResponse> childcomment = commentRepository.findByParentComment(parent.getId()).stream().map(CommentResponse::from).collect(Collectors.toList());
            parent.setReplies((childcomment));
           completeResponse.add(parent);
       }

        return CommentPageResponse.builder()
                .page(parentResponse.getNumber())
                .size(parentResponse.getSize())
                .totalPages(parentResponse.getTotalPages())
                .totalCount(parentResponse.getTotalElements())
                .comments(completeResponse)
                .build();
    }


}
