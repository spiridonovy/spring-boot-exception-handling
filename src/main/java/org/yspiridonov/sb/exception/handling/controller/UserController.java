package org.yspiridonov.sb.exception.handling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yspiridonov.sb.exception.handling.entity.User;
import org.yspiridonov.sb.exception.handling.exception.UserNotFoundException;
import org.yspiridonov.sb.exception.handling.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> find(@PathVariable String username) {
        return userRepository.findByUsername(username).map(user -> ResponseEntity.ok().body(user))
                .orElseThrow(() -> new UserNotFoundException());
    }
}
