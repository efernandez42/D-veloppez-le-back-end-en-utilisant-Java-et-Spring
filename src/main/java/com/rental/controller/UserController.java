package com.rental.controller;

import com.rental.dto.UserResponseMe;
import com.rental.model.User;
import com.rental.service.UserService;
import com.rental.dto.LoginRequest;
import com.rental.dto.RegisterRequest;
import com.rental.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    // Route pour l'inscription
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest registerRequest) {
        User user = userService.register(registerRequest);
        String token = userService.generateJwtToken(user);
        return ResponseEntity.ok(new UserResponse(user, token));
    }

    // Route pour la connexion
    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.login(loginRequest);
        String token = userService.generateJwtToken(user);
        return ResponseEntity.ok(new UserResponse(user, token));
    }

    // Route pour obtenir l'utilisateur connecté
    @GetMapping("/me")
    public ResponseEntity<UserResponseMe> me(@RequestHeader("Authorization") String token) {
        // Supprimer "Bearer " du token
        String cleanToken = token.replace("Bearer ", "");
        User user = userService.getUserFromToken(cleanToken);
        return ResponseEntity.ok(new UserResponseMe(user));
    }
}
