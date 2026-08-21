package com.example.identity_service.service;

import com.example.identity_service.dto.request.UserCreationRequest;
import com.example.identity_service.dto.response.UserResponse;
import com.example.identity_service.entity.User;
import com.example.identity_service.exception.AppException;
import com.example.identity_service.repository.UserRepository;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
@TestPropertySource("/application-test.properties")
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockitoBean
    private UserRepository userRepository;

    private UserCreationRequest userCreationRequest;
    private UserResponse userResponse;
    private User user;
    private LocalDate dateOfBirth;

    @BeforeEach
    public void init() {
        dateOfBirth = LocalDate.of(2000, 02, 02);

        userCreationRequest = UserCreationRequest.builder()
                .username("abcdfe5000")
                .password("abcdfe5000")
                .dateOfBirth(dateOfBirth)
                .build();

        userResponse = UserResponse.builder()
                .id("62b9c80e-42cc-49c0-bb16-143ac2bfb7f3")
                .username("abcdfe5000")
                .dateOfBirth(dateOfBirth)
                .build();

        user = User.builder()
                .id("62b9c80e-42cc-49c0-bb16-143ac2bfb7f3")
                .username("abcdfe5000")
                .dateOfBirth(dateOfBirth)
                .build();
    }

    @Test
    void creatUser_validRequest_success() {
        Mockito.when(userRepository.existsByUsername(Mockito.anyString())).thenReturn(false);
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);

        UserResponse response = userService.createUser(userCreationRequest);

        Assertions.assertEquals("62b9c80e-42cc-49c0-bb16-143ac2bfb7f3", response.getId());
        Assertions.assertEquals("abcdfe5000", response.getUsername());
    }

    @Test
    void createUser_userExisted_fail() {
        Mockito.when(userRepository.save(Mockito.any())).thenThrow(DataIntegrityViolationException.class);

        var exception = Assertions.assertThrows(AppException.class, () -> userService.createUser(userCreationRequest));
        Assertions.assertEquals(1001, exception.getErrorCode().getCode());
    }

    @Test
    @WithMockUser(username = "abcdfe5000")
    void getMyInfo_valid_success() {
        Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.of(user));

        var response = userService.getMyInfo();

        Assertions.assertEquals("abcdfe5000", response.getUsername());
        Assertions.assertEquals("62b9c80e-42cc-49c0-bb16-143ac2bfb7f3", response.getId());
    }

    @Test
    @WithMockUser(username = "abcdfe5000")
    void getMyInfo_userNotFound_error() {
        Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.ofNullable(null));

        var exception = Assertions.assertThrows(AppException.class, () -> userService.getMyInfo());
        Assertions.assertEquals(1003, exception.getErrorCode().getCode());
    }
}
