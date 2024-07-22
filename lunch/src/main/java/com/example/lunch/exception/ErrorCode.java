package com.example.lunch.exception;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorCode {
    INVALID_SESSION("-10");

    private final String code;

    ErrorCode(String code) {
        this.code = code;
    }
    @JsonValue
    public String getCode() {
        return code;
    }
}
