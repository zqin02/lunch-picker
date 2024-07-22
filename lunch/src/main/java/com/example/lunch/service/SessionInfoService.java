package com.example.lunch.service;

import com.example.lunch.bean.SessionInfo;
import com.example.lunch.entity.Session;
import com.example.lunch.utils.UUIDGenerator;
import jakarta.transaction.Transactional;
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

    public SessionInfo getSession(String uuid,String userAlias) {
        Session session = sessionService.getSessionsByUuid(uuid);
        List<String> restaurants = restaurantService.findRestaurantNameBySessionId(uuid);
        return new SessionInfo(session.getUuid(), UUIDGenerator.generateUUID(),userAlias,restaurants);
    }
    @Transactional
    public void endSessionAndCleanUpData(String uuid,String userId) {
        sessionService.endSession(uuid,userId);
        restaurantService.deleteBySessionId(uuid);
    }
}
