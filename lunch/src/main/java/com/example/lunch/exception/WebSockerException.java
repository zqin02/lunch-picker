package com.example.lunch.exception;

public class WebSockerException extends RuntimeException{
    private final String user;

    private final String action;

    public WebSockerException(String message,String user) {
        super(message);
        this.action = "ERROR";
        this.user = user;
        this.setStackTrace(new StackTraceElement[0]);
    }

    public String getUser() {
        return user;
    }

    public String getAction() {
        return action;
    }

}
