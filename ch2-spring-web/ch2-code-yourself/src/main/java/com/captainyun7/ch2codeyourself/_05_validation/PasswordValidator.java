package com.captainyun7.ch2codeyourself._05_validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

// 검증자
public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
    // 검증 로직 적어줘야함

    @Override
    // 맞는지 안맞는지 t/ f 임으로 boolean 반환
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;

        boolean lengthCheck = value.length() >= 8;
        boolean hasUppercase = value.matches(".*[A-Z].*");
        boolean hasLowercase = value.matches(".*[a-z].*");
        boolean hasDigit = value.matches(".*[0-9].*");
        boolean hasSpecial = value.matches(".*[!@#$%^&*()].*");

        // 하나라도 false이면 멈춤. true이면 && 뒤에 체크함.
        return lengthCheck && hasUppercase && hasLowercase && hasDigit && hasSpecial;
    }
}
