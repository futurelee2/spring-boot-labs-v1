package com.example.ch4labs.dto;

import com.example.ch4labs.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewPageResponse {
    private int page;
    private int size;
    private int totalPages;
    private Long totalCount;
    private List<ReviewResponse> ReviewResponse;




    public static ReviewPageResponse from(List<ReviewResponse> reviewResponse, int totalPages, Long totalCount , ReviewSearchRequest search){
        return new ReviewPageResponse(
                search.getPage(), search.getSize(),totalPages,
                totalCount,
                reviewResponse
        );

    }

}
