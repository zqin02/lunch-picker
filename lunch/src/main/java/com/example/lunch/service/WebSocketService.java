package com.example.lunch.service;

import com.example.lunch.bean.RestaurantInfo;
import com.example.lunch.bean.ResultInfo;
import com.example.lunch.bean.SessionInfo;
import com.example.lunch.exception.WebSockerException;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {
    private final static String TOPIC_DEST = "/topic/restaurant.";
    private final static String QUEUE_ERROR_PREFIX_DEST = "/queue/error-";
    private SimpMessageSendingOperations messagingTemplate;

    public WebSocketService(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void connect(SessionInfo sessionInfo) {
        sendMsg(sessionInfo.getUuid(), sessionInfo);
    }
    public void publishSelection(String uuid, ResultInfo restaurant)
    {
        sendMsg(uuid, restaurant);
    }
    public void submitSession(RestaurantInfo msg)
    {
        sendMsg(msg.getUuid(), msg);
    }

    public void notifyError(WebSockerException exception)
    {
        messagingTemplate.convertAndSend(QUEUE_ERROR_PREFIX_DEST+exception.getUser(),exception);
    }
    private void sendMsg(String uuid,Object obj)
    {
        messagingTemplate.convertAndSend(TOPIC_DEST+ uuid, obj);
    }
}
