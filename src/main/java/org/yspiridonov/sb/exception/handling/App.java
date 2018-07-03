package org.yspiridonov.sb.exception.handling;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.yspiridonov.sb.exception.handling.entity.Order;
import org.yspiridonov.sb.exception.handling.entity.User;
import org.yspiridonov.sb.exception.handling.repository.OrderRepository;
import org.yspiridonov.sb.exception.handling.repository.UserRepository;

import java.util.Arrays;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository, OrderRepository orderRepository) {
        return args -> {
            Arrays.asList("Tom", "John", "Bob").forEach(e -> {
                User user = new User();
                user.setUsername(e);
                user.setPassword(e);
                userRepository.save(user);

                Order order = new Order();
                order.setUsername(e);
                orderRepository.save(order);
            });
        };
    }
}
