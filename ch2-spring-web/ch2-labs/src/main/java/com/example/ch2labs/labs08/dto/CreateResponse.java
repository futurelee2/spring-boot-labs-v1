package com.example.ch2labs.labs08.dto;

import com.example.ch2labs.labs08.validation.ValidTimeRange;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@ValidTimeRange // 클래스 유효성 검사임으로. 클래스 위에
public class CreateResponse {
    // userId: 필수, 영문 소문자와 숫자로 구성, 5~12자 사이

    @NotBlank
    @Size(min=5, max=12)
    @Pattern(regexp = "^[a-z0-9]+$")
    private String userId;

    @NotNull
    //@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startTime;
    @NotNull
    //@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endTime;
}
