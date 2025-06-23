package com.example.ch4labs.dto.Review;

import com.example.ch4labs.dto.Comment.CommentResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewWithComments {
    private ReviewResponse review;
    private List<CommentResponse> comments;
}
