package com.example.ch4codeyourself.v5.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentSearchRequest {
    @Builder.Default // 원래 초기화 안되지만 이걸 넣어주면 builder 초기화 됨
    private int page =0; // builder를 쓰면 초기화가 안됨
    @Builder.Default
    private int size=10;
    @Builder.Default
    private boolean hierarchical=true; // 계층구조 , false이면 flat 한 방법

}
