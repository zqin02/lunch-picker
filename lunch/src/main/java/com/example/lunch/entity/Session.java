package com.example.lunch.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;


@Entity
public class Session {
    @Id
    @Column(name = "UUID")
    private String uuid;

    @Column(name = "USER_ID")
    private String user;

    @Column(name = "USER_ALIAS")
    private String userAlias;


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserAlias() {
        return userAlias;
    }

    public void setUserAlias(String userAlias) {
        this.userAlias = userAlias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Session session = (Session) o;
        return Objects.equals(uuid, session.uuid) && Objects.equals(user, session.user) && Objects.equals(userAlias, session.userAlias);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(uuid);
        result = 31 * result + Objects.hashCode(user);
        result = 31 * result + Objects.hashCode(userAlias);
        return result;
    }
}
