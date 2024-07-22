package com.example.lunch.bean;

import java.util.List;

public class SessionInfo{
    private String uuid;
    private String user;
    private String userAlias;

    private List<String> restaurantInfos;

    public SessionInfo() {
    }

    public SessionInfo(String uuid, String user, String userAlias) {
        this.uuid = uuid;
        this.user = user;
        this.userAlias = userAlias;
    }

    public SessionInfo(String uuid, String user, String userAlias,List<String> restaurantInfos) {
        this.uuid = uuid;
        this.user = user;
        this.restaurantInfos = restaurantInfos;
        this.userAlias = userAlias;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<String> getRestaurantInfos() {
        return restaurantInfos;
    }

    public void setRestaurantInfos(List<String> restaurantInfos) {
        this.restaurantInfos = restaurantInfos;
    }

    public String getUserAlias() {
        return userAlias;
    }

    public void setUserAlias(String userAlias) {
        this.userAlias = userAlias;
    }
}
