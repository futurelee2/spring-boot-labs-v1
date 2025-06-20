package com.example.ch4labs.dto.Review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewUpdate {
    private String title;
    private String content;
    private String author;
    private String bookTitle;
    private String bookAuthor;
    private Long rating;

    };

