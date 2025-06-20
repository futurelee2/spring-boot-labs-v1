package com.example.ch4codeyourself.v4.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentCreateRequest {
    private String content;
    private String author;

}
