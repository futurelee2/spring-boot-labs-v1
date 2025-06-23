package com.example.ch4labs.repository.Review;

import com.example.ch4labs.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewQueryRepository  {
    Page<Review> search(Review, pageable);
}
