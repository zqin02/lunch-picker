package com.example.lunch.service;

import com.example.lunch.entity.Session;
import com.example.lunch.exception.NoSessionException;
import com.example.lunch.repository.SessionRepository;
import com.example.lunch.utils.SessionDirector;
import com.example.lunch.utils.UUIDGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class SessionServiceTest {
    @Mock
    private SessionRepository sessionRepository;

    @InjectMocks
    private SessionService sessionService;
    @Test
    void createSession() {

        String userId = UUIDGenerator.generateUUID();
        String userAlias = "test userid";
        String uuid = UUIDGenerator.generateUUID();
        Mockito.when(sessionRepository.save(Mockito.any(Session.class))).thenReturn(SessionDirector.prepareSession(userId,uuid));

        Session newSession = sessionService.createSession(userAlias);

        Mockito.verify(sessionRepository, Mockito.times(1)).save(Mockito.any(Session.class));

        Assertions.assertEquals(uuid, newSession.getUuid());
        Assertions.assertEquals(userId, newSession.getUser());
    }

    @Test
    void endSession() {
        String userId = "test userid";
        String uuid = UUIDGenerator.generateUUID();
        Mockito.when(sessionRepository.findByUuidAndUser(uuid,userId)).thenReturn(Optional.of(SessionDirector.prepareSession(userId,uuid)));

        sessionService.endSession(uuid,userId);
        Mockito.verify(sessionRepository, Mockito.times(1)).delete(Mockito.any(Session.class));
    }

    @Test
    void endSessionWithoutUuid() {
        String userId = "test userid";
        String uuid = UUIDGenerator.generateUUID();
        Assertions.assertThrows(NoSessionException.class,()->{
            Mockito.when(sessionRepository.findByUuidAndUser(uuid,userId)).thenReturn(Optional.empty());
            sessionService.endSession(uuid,userId);
        });

    }

    @Test
    void getSessionsByUuid() {

        String userId = "test userid";
        String uuid = UUIDGenerator.generateUUID();

        Mockito.when(sessionRepository.findById(uuid)).thenReturn(Optional.of(SessionDirector.prepareSession(userId,uuid)));
        Session newSession = sessionService.getSessionsByUuid(uuid);

        Assertions.assertEquals(uuid, newSession.getUuid());
        Assertions.assertEquals(userId, newSession.getUser());
    }

}