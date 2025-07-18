package com.example.ch4labs.dto.Review;

import com.example.ch4labs.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewCreate {
    private String title;
    private String content;
    private String author;
    private String bookTitle;
    private String bookAuthor;
    private Integer rating;

    public Review toDomian() {
        Review review = new Review();
        review.setTitle(this.title);
        review.setContent(this.content);
        review.setAuthor(this.author);
        review.setBookTitle(this.bookTitle);
        review.setBookAuthor(this.bookAuthor);
        review.setRating(this.rating);
        return review;
    };

}

