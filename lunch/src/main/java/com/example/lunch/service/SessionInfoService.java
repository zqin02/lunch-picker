package com.example.lunch.service;

import com.example.lunch.bean.SessionInfo;
import com.example.lunch.entity.Restaurant;
import com.example.lunch.entity.Session;
import com.example.lunch.utils.UUIDGenerator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionInfoService {
    private final SessionService sessionService;
    private final RestaurantService restaurantService;

    public SessionInfoService(SessionService sessionService, RestaurantService restaurantService) {
        this.sessionService = sessionService;
        this.restaurantService = restaurantService;
    }

    public SessionInfo getSession(String uuid) {
        Session session = sessionService.getSessionsByUuid(uuid);
        List<String> restaurants = restaurantService.findRestaurantNameBySessionId(uuid);
        return new SessionInfo(session.getUuid(), UUIDGenerator.generateUUID(),restaurants);
    }
}
