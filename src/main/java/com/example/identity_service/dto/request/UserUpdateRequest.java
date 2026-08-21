package com.example.identity_service.dto.request;

import com.example.identity_service.validator.DayOfBirthConstraint;
import java.time.LocalDate;
import java.util.List;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {

    String password;
    String firstName;
    String lastName;

    @DayOfBirthConstraint(min = 2, message = "INVALID_DOB")
    LocalDate dateOfBirth;

    List<String> roles;
}
