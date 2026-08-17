package com.example.identity_service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error.", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_EXISTED(1001, "User existed.", HttpStatus.BAD_REQUEST),
    INVALID_KEY(1002, "Uncategorized error.", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1003, "User not existed.", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1004, "Password must be at least 8 characters.", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1005, "Unthenticated.", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1006, "You do not have permission.", HttpStatus.FORBIDDEN),
    INVALID_DOB(1007, "Your age must be at least {min}.", HttpStatus.BAD_REQUEST)
    ;

    ErrorCode(int code, String messagge, HttpStatusCode statusCode) {
        this.code = code;
        this.messagge = messagge;
        this.statusCode = statusCode;
    }

    private int code;
    private String messagge;
    private HttpStatusCode statusCode;
}
