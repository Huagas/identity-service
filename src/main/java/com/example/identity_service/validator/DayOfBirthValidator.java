package com.example.identity_service.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class DayOfBirthValidator implements ConstraintValidator<DayOfBirthConstraint, LocalDate> {

    private int min;

    @Override
    public void initialize(DayOfBirthConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (Objects.isNull(value)) {
            return true;
        }

        long years = ChronoUnit.YEARS.between(value, LocalDate.now());
        return years >= min;
    }
}

