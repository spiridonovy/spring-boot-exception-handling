package org.yspiridonov.sb.exception.handling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yspiridonov.sb.exception.handling.entity.Session;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {
    Optional<Session> findByUsername(String username);
}
