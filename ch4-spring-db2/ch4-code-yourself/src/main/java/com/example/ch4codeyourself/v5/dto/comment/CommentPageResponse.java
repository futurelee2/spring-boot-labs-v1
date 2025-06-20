package com.example.ch4codeyourself.v5.dto.comment;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@Builder
public class CommentPageResponse {
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private List<CommentResponse> comments;

    public static CommentPageResponse from(Page<CommentResponse> page){
        return CommentPageResponse.builder()
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .comments(page.getContent())
                .build();
    }


}
