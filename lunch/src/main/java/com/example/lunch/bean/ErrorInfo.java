package com.example.lunch.bean;

import com.example.lunch.exception.ErrorCode;

public class ErrorInfo {
    private ErrorCode errorCode;
    private String message;

    public ErrorInfo(ErrorCode errorCode, String msg) {
        this.errorCode = errorCode;
        this.message = msg;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
