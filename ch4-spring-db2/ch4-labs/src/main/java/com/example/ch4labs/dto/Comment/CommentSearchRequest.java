package com.example.ch4labs.dto.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentSearchRequest {
    @Builder.Default
    private boolean includeComments = false;
    @Builder.Default
    private Integer commentPage = 0;
    @Builder.Default
    private Integer commentSize = 5;
    @Builder.Default
    private boolean hierarchical=true;
}
