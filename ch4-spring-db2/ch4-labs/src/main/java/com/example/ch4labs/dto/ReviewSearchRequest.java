package com.example.ch4labs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewSearchRequest {
    private String bookTitle;
    private String author;
    private int rating;
    private int minRating;
    private int maxRating;
    private int page = 0;
    private int size =10;
}
