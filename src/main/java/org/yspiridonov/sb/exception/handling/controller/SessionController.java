package org.yspiridonov.sb.exception.handling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yspiridonov.sb.exception.handling.entity.Session;
import org.yspiridonov.sb.exception.handling.exception.SessionNotFoundException;
import org.yspiridonov.sb.exception.handling.exception.UserNotFoundException;
import org.yspiridonov.sb.exception.handling.repository.SessionRepository;
import org.yspiridonov.sb.exception.handling.repository.UserRepository;

@RestController
@RequestMapping("/session")
public class SessionController {
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;

    @Autowired
    public SessionController(SessionRepository sessionRepository, UserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping("{username}")
    public ResponseEntity<Session> find(@PathVariable String username) {
        validateUser(username);
        return sessionRepository.findByUsername(username)
                .map(session -> ResponseEntity.ok().body(session))
                .orElseThrow(() -> new SessionNotFoundException("Session not found for " + username));
    }

    private void validateUser(String username) {
        userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException());
    }
}
