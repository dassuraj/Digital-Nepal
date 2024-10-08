package com.security.webtech.digitalnepalfullstackcrud.controller;

import com.security.webtech.digitalnepalfullstackcrud.entity.RegisterRequest;
import com.security.webtech.digitalnepalfullstackcrud.repository.UserRepository;
import com.security.webtech.digitalnepalfullstackcrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        if (userRepository.existsByUserName(request.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already taken!");
        }
        userService.registerUser(request.getUsername(), request.getPassword(), request.getRole());

        return ResponseEntity.ok("User registered successfully!");
    }
}
