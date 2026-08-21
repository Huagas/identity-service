package com.example.identity_service.dto.request;

import com.example.identity_service.validator.DayOfBirthConstraint;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {

    String username;

    @Size(min = 8, message = "PASSWORD_INVALID")
    String password;

    String firstName;
    String lastName;

    @DayOfBirthConstraint(min = 2, message = "INVALID_DOB")
    LocalDate dateOfBirth;
}
