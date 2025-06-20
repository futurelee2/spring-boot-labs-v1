package com.example.ch4codeyourself.v4.dto.comment;

import com.example.ch4codeyourself.v4.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder // 생성자보다 편하게 만들 수 있음
public class CommentResponse {
    private Long id;
    private String content;
    private String author;
    private LocalDateTime createdAt;
    private Long postId;

    public static CommentResponse from(Comment comment) {
        // new 로하면 생성자 매개변수 순서를 다 맞춰줘야함. => 현 개발환경에서는 변수가 많아서 힘듦. new 필요 X
        // builder 패턴을 사용하게되면 순서 상관없이 만들 수 있음
        return CommentResponse.builder().id(comment.getId())
                .content(comment.getContent())
                .author(comment.getAuthor())
                .createdAt(comment.getCreatedAt())
                .postId(comment.getPost().getId()).build();

    }

}
