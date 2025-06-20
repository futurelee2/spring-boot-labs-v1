package com.example.ch4codeyourself.v5.service;

import com.example.ch4codeyourself.v5.domain.Comment;
import com.example.ch4codeyourself.v5.domain.Post;
import com.example.ch4codeyourself.v5.dto.comment.*;
import com.example.ch4codeyourself.v5.repository.CommentRepository;
import com.example.ch4codeyourself.v5.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentResponse createComment(Long postId, CommentCreateRequest request) {

        // post 를 가져오기 위해서 postService 를 가져오지않고 repository 가져옴
        Post post = postRepository.findById(postId).orElseThrow(()-> new EntityNotFoundException("Post not found"));


        if (request.getParentId() != null) {
            Comment parent =  commentRepository.findById(request.getParentId()).orElseThrow(()-> new EntityNotFoundException("Comment not found"));
        }

        Comment comment = Comment.builder()
                .content(request.getContent())
                .author(request.getAuthor())
                .createdAt(LocalDateTime.now())
                .post(post)
                .build();
        if (request.getParentId() != null) {
            Comment parent =  commentRepository.findById(request.getParentId()).orElseThrow(()-> new EntityNotFoundException("Comment not found"));
            comment.setParent(parent);
        }

        Comment saved = commentRepository.save(comment);

        return CommentResponse.from(saved);
    }



    public CommentPageResponse getCommentsByPost(Long postId, CommentSearchRequest request) {
        
        // 댓글 + 대댓글... 모든 댓글 page 정보 가져오기
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by(Sort.Direction.DESC, "createdAt"));

        // boolean은 get,set 대신 is로 사용
        if(request.isHierarchical()) {
            // 계층 구조로 보여주기
            return getHierarchicalCommentByPost(postId, pageable);

        }else{
            // 플랫구조로 보여주기
            return getFlatCommentsByPost(postId, pageable);
        }


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

    // 여기 안에서 사용하는거면 private
    private CommentPageResponse getFlatCommentsByPost(Long postId, Pageable pageable) {
        Page<CommentResponse> comments= commentRepository.findByPostId(postId, pageable).map(CommentResponse:: from); // page 정보 + 하나의 post의 댓글들!
        return CommentPageResponse.from(comments);
    }


    private CommentPageResponse getHierarchicalCommentByPost(Long postId, Pageable pageable) {
        // 계층형 응답
        // 부모댓글 가져와서 + 부모 댓글의 페이징
        // 부모댓글을 통해 자식 댓글 가져오기

        Page<Comment> page = commentRepository.findByPostIdAndParentIdNull(postId, pageable);

        // 부모 + 대댓글 다 담음
        List<CommentResponse> completeComments = new ArrayList<>();

        // 부모 댓글 한개씩 가져와서
        for(Comment parentComment : page.getContent()) {
            // 자식 댓글 가져오기
            List<Comment> childComments = commentRepository.findByParentIdOrderByCreatedAtDesc(parentComment.getId()); // postId 아님
            CommentResponse parentCommentResponse = CommentResponse.from(parentComment);
            parentCommentResponse.setReplies(childComments.stream().map(CommentResponse::from).collect(Collectors.toList()));
            completeComments.add(parentCommentResponse);

        }
        return CommentPageResponse.builder()
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .comments(completeComments)
                .build()
                ;
    }

}
