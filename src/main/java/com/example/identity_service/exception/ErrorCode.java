package com.example.identity_service.exception;

public enum ErrorCode {
    USER_EXISTED(1001, "User existed."),
    USER_NOT_EXISTED(1002, "User not existed."),
    PASSWORD_INVALID(1003, "Password must be at least 8 characters."),
    UNAUTHENTICATED(1004, "Unthenticated.")
    ;

    ErrorCode(int code, String messagge) {
        this.code = code;
        this.messagge = messagge;
    }

    private int code;
    private String messagge;

    public String getMessagge() {
        return messagge;
    }

    public void setMessagge(String messagge) {
        this.messagge = messagge;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
