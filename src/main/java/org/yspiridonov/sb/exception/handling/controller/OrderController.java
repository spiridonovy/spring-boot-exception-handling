package org.yspiridonov.sb.exception.handling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yspiridonov.sb.exception.handling.entity.Order;
import org.yspiridonov.sb.exception.handling.exception.OrderNotFoundException;
import org.yspiridonov.sb.exception.handling.repository.OrderRepository;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("{username}")
    public ResponseEntity<Order> find(@PathVariable String username) {
        return orderRepository.findByUsername(username)
                .map(order -> ResponseEntity.ok().body(order))
                .orElseThrow(() -> new OrderNotFoundException("Order for " + username + " not found"));
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity handle(OrderNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
