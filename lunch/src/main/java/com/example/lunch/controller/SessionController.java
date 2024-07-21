package com.example.lunch.controller;

import com.example.lunch.bean.ResultInfo;
import com.example.lunch.bean.SessionInfo;
import com.example.lunch.entity.Session;
import com.example.lunch.service.RestaurantService;
import com.example.lunch.service.SessionInfoService;
import com.example.lunch.service.SessionService;
import com.example.lunch.service.WebSocketService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/session")
public class SessionController {
    private final SessionService sessionService;
    private final RestaurantService restaurantService;
    private final WebSocketService webSocketService;
    private final SessionInfoService sessionInfoService;

    public SessionController(SessionService sessionService, RestaurantService restaurantService, WebSocketService webSocketService, SessionInfoService sessionInfoService) {
        this.sessionService = sessionService;
        this.restaurantService = restaurantService;
        this.webSocketService = webSocketService;
        this.sessionInfoService = sessionInfoService;
    }


    @PostMapping("/create")
    public SessionInfo createSession() {
        Session session = sessionService.createSession();
        SessionInfo sessionInfo = new SessionInfo(session.getUuid(),session.getUser());
        webSocketService.connect(sessionInfo);
        return sessionInfo;
    }

    @PostMapping("/end")
    public void endSession(@RequestBody SessionInfo info) {
        ResultInfo resultInfo = restaurantService.pickRandomRestaurant(info.getUuid());
        webSocketService.publishSelection(info.getUuid(),resultInfo);
        sessionService.endSession(info.getUuid(),info.getUser());

    }

    @GetMapping("/connect/{uuid}")
    public SessionInfo getSessionsByUUID(@PathVariable String uuid) {
        SessionInfo session = sessionInfoService.getSession(uuid);
        webSocketService.connect(session);
        return session;
    }
}