package com.example.ch4labs.dto.Review;

import com.example.ch4labs.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponse {
    private Long id;
    private String title;
    private String content;
    private String  author;
    private String bookTitle;
    private String bookAuthor;
    private Integer rating;


    //
    public static ReviewResponse from(Review review) {
        return new ReviewResponse(review.getId(), review.getTitle(), review.getContent(), review.getAuthor(), review.getBookTitle(), review.getBookAuthor(), review.getRating());

    }
}
