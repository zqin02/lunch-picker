package com.example.lunch.repository;

import com.example.lunch.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, String> {

    Optional<Session> findByUuidAndUser(String uuid, String userId);
}