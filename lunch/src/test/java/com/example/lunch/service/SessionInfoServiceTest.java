package com.example.lunch.service;

import com.example.lunch.bean.SessionInfo;
import com.example.lunch.entity.Session;
import com.example.lunch.utils.UUIDGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class SessionInfoServiceTest {

    @Mock
    private SessionService sessionService;

    @Mock
    private RestaurantService restaurantService;

    @InjectMocks
    private SessionInfoService sessionInfoService;



    @Test
    void testGetSession() {
        String uuid = UUIDGenerator.generateUUID();
        String userAlias = "user-1";
        Session session = new Session();
        session.setUuid(uuid);

        List<String> restaurants = Arrays.asList("Test Restaurant 1", "Test Restaurant 2");

        Mockito.when(sessionService.getSessionsByUuid(uuid)).thenReturn(session);
        Mockito.when(restaurantService.findRestaurantNameBySessionId(uuid)).thenReturn(restaurants);

        SessionInfo sessionInfo = sessionInfoService.getSession(uuid, userAlias);

        assertEquals(uuid, sessionInfo.getUuid());
        assertEquals(userAlias, sessionInfo.getUserAlias());
        assertEquals(restaurants, sessionInfo.getRestaurantInfos());
        Mockito.verify(sessionService, Mockito.times(1)).getSessionsByUuid(uuid);
        Mockito.verify(restaurantService, Mockito.times(1)).findRestaurantNameBySessionId(uuid);
    }

    @Test
    void testEndSessionAndCleanUpData() {
        String uuid = UUIDGenerator.generateUUID();
        String userId = UUIDGenerator.generateUUID();

        Mockito.doNothing().when(sessionService).endSession(uuid, userId);
        Mockito.doNothing().when(restaurantService).deleteBySessionId(uuid);

        sessionInfoService.endSessionAndCleanUpData(uuid, userId);

        Mockito.verify(sessionService, Mockito.times(1)).endSession(uuid, userId);
        Mockito.verify(restaurantService, Mockito.times(1)).deleteBySessionId(uuid);
    }
}