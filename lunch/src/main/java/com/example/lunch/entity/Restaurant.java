package com.example.lunch.entity;

import com.example.lunch.bean.RestaurantInfo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

import java.util.Objects;

@Entity
@IdClass(RestaurantPK.class)
public class Restaurant {

    @Id
    @Column(name = "USER_ID")
    private String user;

    @Id
    @Column(name = "RESTAURANT_NAME")
    private String restaurantName;

    @Id
    @Column(name = "SESSION_ID")
    private String sessionId;

    public Restaurant() {
    }

    public Restaurant(RestaurantInfo restaurantInfo) {
        this.user = restaurantInfo.getUser();
        this.restaurantName = restaurantInfo.getRestaurantName();
        this.sessionId = restaurantInfo.getUuid();
    }
    public Restaurant(String user, String restaurantName, String sessionId) {
        this.user = user;
        this.restaurantName = restaurantName;
        this.sessionId = sessionId;
    }

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
        Restaurant that = (Restaurant) o;
        return Objects.equals(user, that.user) && Objects.equals(restaurantName, that.restaurantName) && Objects.equals(sessionId, that.sessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, restaurantName, sessionId);
    }
}
