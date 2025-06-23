package com.example.ch4codeyourself.v5.dto.comment;

import com.example.ch4codeyourself.v5.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder // 생성자보다 편하게 만들 수 있음
public class CommentResponse {
    private Long id;
    private Long parentId;
    private String content;
    private String author;
    private LocalDateTime createdAt;
    private Long postId;
    private List<CommentResponse> replies = new ArrayList<>(); // 댓글의 댓글을 계층형으로 표현하기 위해서 사용.

    public static CommentResponse from(Comment comment) {
        // new 로하면 생성자 매개변수 순서를 다 맞춰줘야함. => 현 개발환경에서는 변수가 많아서 힘듦. new 필요 X
        // builder 패턴을 사용하게되면 순서 상관없이 만들 수 있음
        // 지정하지 않은 필드는 초기값(기본값) 으로 사용됨
        return CommentResponse.builder().id(comment.getId())
                .parentId(comment.getParent()!=null? comment.getParent().getId():null)
                .content(comment.getContent())
                .author(comment.getAuthor())
                .createdAt(comment.getCreatedAt())
                .postId(comment.getPost().getId()).build();

    }

}
