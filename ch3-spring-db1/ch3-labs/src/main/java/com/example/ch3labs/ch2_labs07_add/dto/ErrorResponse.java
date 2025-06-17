package com.example.ch3labs.ch2_labs07_add.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class ErrorResponse {
    private String code;
    private String message;
}
