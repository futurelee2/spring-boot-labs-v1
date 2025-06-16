package com.captainyun7.ch2codeyourself._05_validation;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

//DTO
@Getter
@Setter
public class SignupRequest {
    @NotBlank(message = "아이디는 필수입니다.") //jakarta로 써줘야함, exception이 터질때 메세지가 같이 보여짐
    @Size(min=5,max=10, message = "아이디는 5~10자리입니다.")
    private String username;

    @ValidPassword
    private String password;

    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @Min(value=14, message = "만 14세 이상만 가입할 수 있습니다.")
    private Integer age;

    @AssertTrue(message = "약관 동의 필수입니다.")
    private Boolean agreeTerms;

}
