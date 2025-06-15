package com.example.ch2labs.labs08.validation;

import java.lang.annotation.*;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;


// 커스텀 어노테이션을 정의하는 부분
// 이 부분에서 정의한 메세지는 내부적으로 저장만 함. 이 값을 사용자에게 노출하려면 Global핸들러에서 꺼내서 반환해야함

@Documented
@Constraint(validatedBy = TimeValidator.class)
@Target({ElementType.TYPE}) // 클래스 유효성 검사 -> 필드 유효성 검사는 해당 필드 하나만 보고 판단함. 따라서 클래스 유효성 검사는 두 값 비교 가능
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTimeRange {
    String message() default "시작 시간은 종료 시간보다 이전이어야 합니다.";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
