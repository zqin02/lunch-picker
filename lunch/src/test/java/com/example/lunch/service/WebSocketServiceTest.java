package com.example.lunch.service;

import com.example.lunch.bean.RestaurantInfo;
import com.example.lunch.exception.WebSockerException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.simp.SimpMessagingTemplate;
@ExtendWith(MockitoExtension.class)
class WebSocketServiceTest {
    @Mock
    private SimpMessagingTemplate messagingTemplate;

    @InjectMocks
    private WebSocketService webSocketService;


    @Test
    public void testSubmitMessage() {
        String name = "Test Restaurant";
        String userId = "usert";
        String sessionId = "sessionkey";
        String destination = WebSocketService.TOPIC_DEST+sessionId;
        RestaurantInfo restaurantInfo = new RestaurantInfo(sessionId,name,userId);
        webSocketService.submitSession(restaurantInfo);

        Mockito.verify(messagingTemplate).convertAndSend(destination, restaurantInfo);
    }


    @Test
    void notifyError() {
        String userId = "usert";
        String destination = WebSocketService.QUEUE_ERROR_PREFIX_DEST+userId;
        WebSockerException ex = new WebSockerException("Invalid",userId);
        webSocketService.notifyError(ex);

        Mockito.verify(messagingTemplate).convertAndSend(destination, ex);
    }

}