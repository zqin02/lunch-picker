package com.example.lunch.utils;

import com.example.lunch.bean.SessionInfo;
import com.example.lunch.entity.Session;

public class SessionDirector {
    public static Session prepareSession(String userId, String uuid){

        Session session = new Session();
        session.setUser(userId);
        session.setUuid(uuid);
        return session;
    }
    public static Session prepareSession(String alias){

        Session session = new Session();
        session.setUser(UUIDGenerator.generateUUID());
        session.setUuid(UUIDGenerator.generateUUID());
        session.setUserAlias(alias);
        return session;
    }
    public static SessionInfo prepareSessionInfo(String alias){

        SessionInfo session = new SessionInfo();
        session.setUser(UUIDGenerator.generateUUID());
        session.setUuid(UUIDGenerator.generateUUID());
        session.setUserAlias(alias);
        return session;
    }
}
