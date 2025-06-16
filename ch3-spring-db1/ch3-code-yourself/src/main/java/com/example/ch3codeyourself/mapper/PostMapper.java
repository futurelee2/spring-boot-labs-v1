package com.example.ch3codeyourself.mapper;

import com.example.ch3codeyourself.domain.Post;
import com.example.ch3codeyourself.dto.PostResponse;
import com.example.ch3codeyourself.dto.PostSearchRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper // mybatis 의존성 , 런타임에 읽어서 xml 파일과 연동?
public interface PostMapper {

    // 저장하면서 자동적으로 id 가 같이 저장됨. mybatis에서 처리해줌
    void save(Post post); // 객체를 반환 받을 수 없음

    List<Post> findAll();

    Post findOne(Long id);

    void delete(Long id);

    void update(Post post);

    List<Post> findAllWithSearch(PostSearchRequest search);
}
