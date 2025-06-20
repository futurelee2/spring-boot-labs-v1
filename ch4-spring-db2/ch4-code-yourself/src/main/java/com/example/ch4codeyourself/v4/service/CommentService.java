package com.example.ch4codeyourself.v4.service;

import com.example.ch4codeyourself.v4.domain.Comment;
import com.example.ch4codeyourself.v4.domain.Post;
import com.example.ch4codeyourself.v4.dto.comment.CommentCreateRequest;
import com.example.ch4codeyourself.v4.dto.comment.CommentPageResponse;
import com.example.ch4codeyourself.v4.dto.comment.CommentResponse;
import com.example.ch4codeyourself.v4.dto.comment.CommentUpdateRequest;
import com.example.ch4codeyourself.v4.repository.CommentRepository;
import com.example.ch4codeyourself.v4.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentResponse createComment(Long postId, CommentCreateRequest request) {

        // post 를 가져오기 위해서 postService 를 가져오지않고 repository 가져옴
        Post post = postRepository.findById(postId).orElseThrow(()-> new EntityNotFoundException("Post not found"));

        Comment comment = Comment.builder()
                .content(request.getContent())
                .author(request.getAuthor())
                .createdAt(LocalDateTime.now())
                .post(post)
                .build();
        Comment saved = commentRepository.save(comment);

        return CommentResponse.from(saved);
    }

    public CommentPageResponse getCommentsByPost(Long postId, int page, int size) {
        Page<Comment> comments= commentRepository.findByPostId(postId, PageRequest.of(page,size));
        return CommentPageResponse.from(comments.map(CommentResponse::from));
    }

    public CommentResponse updateComment(Long commentsId, CommentUpdateRequest request) {
        Comment comment = commentRepository.findById(commentsId).orElseThrow(()-> new EntityNotFoundException("Comment not found"));
        comment.setContent(request.getContent());
        return CommentResponse.from(comment);

    }

    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new IllegalArgumentException("존재하는 댓글이 없습니다."));
        commentRepository.delete(comment);
    }
}
