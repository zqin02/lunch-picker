package com.example.lunch.repository;

import com.example.lunch.entity.Restaurant;
import com.example.lunch.entity.RestaurantPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, RestaurantPK> {

    List<Restaurant> findBySessionId(String sessionId);
    Restaurant findBySessionIdAndRestaurantName(String sessionId,String restaurantName);
}