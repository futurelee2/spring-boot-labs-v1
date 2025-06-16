package com.captainyun7.ch2codeyourself._04_3tiers_crud.repository;

import com.captainyun7.ch2codeyourself._04_3tiers_crud.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PostRepository {
    
    
    // db
    // map = key (중복 X, 고유값 넣어줘야함) , value 로 저장함
    // 즉, Long: id 타입임
    private final Map<Long, Post> store = new HashMap<>(); // pojo -> 빈 안쓰여서 @Require~~ 안붙여도됨
    private Long sequence = 0L;


    // 게시글 CRUD = Create, Read, Update, Delete

    // save하면 자동 업데이트 됨?? 그래서 따로 업데이트 안함.
    public Post save(Post post){ // 서비스가 게시글 하나 넣어달라고 이 함수 호출함
        post.setId(++sequence);
        store.put(post.getId(), post); // (id, 게시글)
        return post; // rest api 형식임
    }

//    public Post get(Long id){
//         return store.get(id);
    
    // null 값 있을수도 있음, Optional로 묶어주면 null 처리 쉬움
    public Optional<Post> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // 메서드를 활용해서 null 값 확인
        // null 이 아니면 값을 리턴. null 이면 null 옵셔널 리턴?

    }

    public List<Post> findAll(){
        return new ArrayList<>(store.values());
        }

    public void delete(Long id){
        store.remove(id);
    }


}
