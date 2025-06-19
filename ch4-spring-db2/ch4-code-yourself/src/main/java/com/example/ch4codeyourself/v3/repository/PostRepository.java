package com.example.ch4codeyourself.v3.repository;

import com.example.ch4codeyourself.v3.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

//JPQL !!
@Repository
public interface PostRepository extends JpaRepository<Post, Long>, PostQueryRepository{
    //crud
    // 메서드들을 자동 추상화함. 아무것도 안만들어도 crud가 만들어짐
    // Post 를 읽어서 자동으로 만들어줌
    
    // jpa 가 구현체를 알아서 만들어줌
    // post 를 찾을때 키워드.. 를 포함해서 찾아줌
    // 이걸 자동생성 메서드 라고 함. (= JPA 가 메서드명만 보고 자동으로 만들어줌)
    // Page 를 반환받고 싶으면 무조건 pageable을 넣어줘야하는 규칙이 있음
    // Page 반환 안받으려면 List 로 반환받기
    Page<Post> findByTitleContaining(String keyword, Pageable pageable); // 예약어
    //List<Post> findByTitleContaining(String keyword);

    // 작성자 일치 조건
    //findBy[필드][조건] AND|OR [필드][조건][정렬] => 알아서 sql로 변환해서 보냄
    Page<Post> findByAuthor(String author, Pageable pageable);

    // 제목 검색어 포함 + 작성자 일치
    Page<Post> findByTitleContainingAndAuthor(String keyword ,String author, Pageable pageable);

    //JPQL로 제목 검색어 포함
    @Query("SELECT p FROM Post p WHERE p.title LIKE %:keyword%  AND p.author LIKE :author ")
    Page<Post> searchByTitleContainingAndAuthor(@Param("keyword")  String keyword , @Param("author") String author, Pageable pageable);

    // 작성일자 이후에 대한 조회
    Page<Post> findByCreatedAtAfter(LocalDateTime createdAt, Pageable pageable);

    //JPQL 로 작성일자 이후 조회
    //SELECT * FROM posts WHERE created_at <= '2025-06-10T00:00'
    @Query("SELECT p FROM Post p WHERE p.createdAt >= :createdAt ")
    Page<Post> searchByAuthorAndTitle(@Param("createdAt") LocalDateTime createdAt, Pageable pageable);


}
