package com.example.lunch.service;

import com.example.lunch.bean.SessionInfo;
import com.example.lunch.entity.Restaurant;
import com.example.lunch.entity.Session;
import com.example.lunch.exception.NoSessionException;
import com.example.lunch.repository.RestaurantRepository;
import com.example.lunch.repository.SessionRepository;
import com.example.lunch.utils.UUIDGenerator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {
    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public Session createSession() {
        Session session = new Session();
        session.setUuid(UUIDGenerator.generateUUID());
        session.setUser(UUIDGenerator.generateUUID());
        return sessionRepository.save(session);
    }

    public void endSession(String uuid, String userId) {
        Session session = sessionRepository.findByUuidAndUser(uuid,userId).orElseThrow(()->new NoSessionException());
        sessionRepository.delete(session);
    }

    public Session getSessionsByUuid(String uuid) {
        return sessionRepository.findById(uuid).orElseThrow(()->new NoSessionException());
    }
}