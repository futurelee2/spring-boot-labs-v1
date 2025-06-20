package com.example.ch4labs.repository.Review;

import com.example.ch4labs.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> , ReviewQueryRepository {
    Page<Review> findByTitleContaining(String bookTitle, Pageable pageable);
}

