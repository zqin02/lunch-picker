package com.example.lunch.bean;

public class CreateSessionRequest {
    private String userAlias;

    public CreateSessionRequest() {
    }

    public CreateSessionRequest(String userAlias) {
        this.userAlias = userAlias;
    }

    public String getUserAlias() {
        return userAlias;
    }

    public void setUserAlias(String userAlias) {
        this.userAlias = userAlias;
    }
}
