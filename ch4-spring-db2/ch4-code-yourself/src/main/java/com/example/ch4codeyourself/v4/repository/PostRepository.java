package com.example.ch4codeyourself.v4.repository;

import com.example.ch4codeyourself.v3.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

//JPQL !!
// JPA의 기본 CRUD 기능 + 커스텀 QueryDSL 기능을 함께 사용하기 위해 PostQueryRepository 을 같이 상속해줌
// 사용자 정의 메서드를 따로 구현할 수 있음
@Repository
public interface PostRepository extends JpaRepository<com.example.ch4codeyourself.v4.domain.Post, Long>, PostQueryRepository {
    // Page 를 반환받고 싶으면 무조건 pageable을 넣어줘야하는 규칙이 있음
    // Page 반환 안받으려면 List 로 반환받기
    Page<com.example.ch4codeyourself.v4.domain.Post> findByTitleContaining(String keyword, Pageable pageable); // 예약어
    //List<Post> findByTitleContaining(String keyword);

    // 작성자 일치 조건
    //findBy[필드][조건] AND|OR [필드][조건][정렬] => 알아서 sql로 변환해서 보냄
    Page<com.example.ch4codeyourself.v4.domain.Post> findByAuthor(String author, Pageable pageable);

    // 제목 검색어 포함 + 작성자 일치
    Page<com.example.ch4codeyourself.v4.domain.Post> findByTitleContainingAndAuthor(String keyword , String author, Pageable pageable);

    //JPQL로 제목 검색어 포함
    @Query("SELECT p FROM Post p WHERE p.title LIKE %:keyword%  AND p.author LIKE :author ") // 정확히 일치
    Page<com.example.ch4codeyourself.v4.domain.Post> searchByTitleContainingAndAuthor(@Param("keyword")  String keyword , @Param("author") String author, Pageable pageable);

    // 작성일자 이후에 대한 조회
    Page<com.example.ch4codeyourself.v4.domain.Post> findByCreatedAtAfter(LocalDateTime createdAt, Pageable pageable);

    //JPQL 로 작성일자 이후 조회
    //SELECT * FROM posts WHERE created_at <= '2025-06-10T00:00'
    @Query("SELECT p FROM Post p WHERE p.createdAt >= :createdAt ")
    Page<com.example.ch4codeyourself.v4.domain.Post> searchByAuthorAndTitle(@Param("createdAt") LocalDateTime createdAt, Pageable pageable);


}
