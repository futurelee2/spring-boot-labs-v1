package com.example.ch4labs.repository.Review;

import com.example.ch4labs.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends  JpaRepository<Review, Long> ,ReviewQueryRepository {
    Page<Review> findByBookTitleContaining(String bookTitle, Pageable pageable);

    @Query("select r from Review r where r.author like :author and r.rating >= :rating ")
    Page<Review> searchByAuthorRating(@Param("author") String author, @Param("rating") Integer rating, Pageable pageable);

    @Query("select r from Review r where r.rating between :minRating and :maxRating ")
    Page<Review> searchMinMaxRating(@Param("minRating") Integer minRating, @Param("maxRating") Integer maxRating, Pageable pageable);


}

