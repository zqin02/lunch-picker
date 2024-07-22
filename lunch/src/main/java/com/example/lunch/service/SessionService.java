package com.example.lunch.service;

import com.example.lunch.entity.Session;
import com.example.lunch.exception.NoSessionException;
import com.example.lunch.repository.SessionRepository;
import com.example.lunch.utils.UUIDGenerator;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public Session createSession(String userAlias) {
        Session session = new Session();
        session.setUuid(UUIDGenerator.generateUUID());
        session.setUser(UUIDGenerator.generateUUID());
        session.setUserAlias(userAlias);
        return sessionRepository.save(session);
    }

    public void endSession(String uuid, String userId) {
        Session session = sessionRepository.findByUuidAndUser(uuid,userId).orElseThrow(NoSessionException::new);
        sessionRepository.delete(session);
    }

    public Session getSessionsByUuid(String uuid) {
        return sessionRepository.findById(uuid).orElseThrow(NoSessionException::new);
    }
}