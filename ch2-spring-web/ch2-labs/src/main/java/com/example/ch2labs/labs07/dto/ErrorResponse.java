package com.example.ch2labs.labs07.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class ErrorResponse {
    private String code;
    private String message;
}
