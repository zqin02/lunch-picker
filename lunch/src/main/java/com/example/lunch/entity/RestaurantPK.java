package com.example.lunch.entity;

import java.io.Serializable;
import java.util.Objects;

public class RestaurantPK implements Serializable {

    private String user;

    private String restaurantName;

    private String sessionId;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantPK that = (RestaurantPK) o;
        return Objects.equals(user, that.user) && Objects.equals(restaurantName, that.restaurantName) && Objects.equals(sessionId, that.sessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, restaurantName, sessionId);
    }
}
