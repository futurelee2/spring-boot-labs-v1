package com.captainyun7.ch2codeyourself._05_validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class ) // 누구한테 검증을 받을거냐? 따로 만들어야함
@Target({ElementType.FIELD}) // 필드 적용
@Retention(RetentionPolicy.RUNTIME) // 런타임에 적용됨
public @interface ValidPassword {
    // 커스텀 어노테이션은 interface 앞에 @ 붙이면 됨
    //인터페이스의 속성 정의함
    String message() default "비밀번호는 8자 이상 & 숫자, 대문자, 특수문자를 포함해야 합니다.";
    // 아래 두개는 필수적으로 넣어줘야함
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
