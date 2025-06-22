package com.example.ch4labs.dto.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentPageResponse {
    private int page;
    private int size;
    private int totalPages;
    private Long totalCount;
    private List<CommentResponse> comments;

    public static CommentPageResponse from(Page<CommentResponse> comments) {
        return new CommentPageResponse(
                comments.getNumber(),
                comments.getSize(),
                comments.getTotalPages(),
                comments.getTotalElements(),
                comments.getContent()
        );
    }
}
