package com.example.lunch.controller;

import com.example.lunch.bean.RestaurantInfo;
import com.example.lunch.service.RestaurantService;
import com.example.lunch.service.WebSocketService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final WebSocketService webSocketService;

    public RestaurantController(RestaurantService restaurantService, WebSocketService webSocketService) {
        this.restaurantService = restaurantService;
        this.webSocketService = webSocketService;
    }

    @MessageMapping("/submit")
    public void submitRestaurant(RestaurantInfo restaurantInfo) {
        restaurantService.submitRestaurant(restaurantInfo);
        webSocketService.submitSession(restaurantInfo);
    }


}