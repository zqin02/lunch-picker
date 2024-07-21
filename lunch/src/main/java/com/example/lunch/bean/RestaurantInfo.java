package com.example.lunch.bean;

public class RestaurantInfo implements BaseAction{
    private String uuid;
    private String restaurantName;
    private String user;

    private final static String UPDATE_ACTION = "UPDATE";

    public RestaurantInfo() {

    }

    public RestaurantInfo(String uuid, String restaurantName, String user) {
        this.uuid = uuid;
        this.restaurantName = restaurantName;
        this.user = user;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String getAction() {
        return UPDATE_ACTION;
    }
}
