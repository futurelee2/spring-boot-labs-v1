package com.example.ch4labs.repository.Review;

import com.example.ch4labs.dto.Review.ReviewResponse;
import com.example.ch4labs.dto.Review.ReviewSearchRequest;
import org.springframework.data.domain.Page;

public interface ReviewQueryRepository{
    Page<ReviewResponse> search(ReviewSearchRequest request);
}
