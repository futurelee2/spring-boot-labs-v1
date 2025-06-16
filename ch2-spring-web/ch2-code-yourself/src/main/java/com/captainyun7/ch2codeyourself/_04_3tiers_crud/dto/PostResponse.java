package com.captainyun7.ch2codeyourself._04_3tiers_crud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor // json 변환시 필요할 수도
public class PostResponse {
    private Long id;
    private String title;
    private String body;

}
