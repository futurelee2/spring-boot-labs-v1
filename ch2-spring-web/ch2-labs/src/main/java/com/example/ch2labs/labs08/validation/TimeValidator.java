package com.example.ch2labs.labs08.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import com.example.ch2labs.labs08.dto.CreateResponse;


// 실제 사용자 예외 로직을 처리하는 부분
public class TimeValidator implements ConstraintValidator<ValidTimeRange, CreateResponse> { // <어노테이션, 유효성 검사 타입>
    @Override
    public boolean isValid(CreateResponse value, ConstraintValidatorContext context) { //(유효성 검사 값, 사용자 정의 오류메세지 설정 가능(사용안해도 됨))
        if(value==null || value.getStartTime()==null || value.getEndTime()==null){
            return true; // true 이면 @NotNull 에서 검사
        }
        return value.getStartTime().isBefore(value.getEndTime());
    }
}
