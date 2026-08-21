package com.example.identity_service.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {DayOfBirthValidator.class})
public @interface DayOfBirthConstraint {
    String message() default "Invalid day of birth.";

    int min();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
