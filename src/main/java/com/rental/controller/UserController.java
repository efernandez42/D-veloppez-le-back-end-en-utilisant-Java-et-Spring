package com.rental.controller;

import com.rental.dto.UserResponseMe;
import com.rental.model.User;
import com.rental.service.UserService;
import com.rental.dto.LoginRequest;
import com.rental.dto.RegisterRequest;
import com.rental.dto.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentification", description = "Endpoints pour l'inscription, la connexion et l'accès au profil utilisateur.")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Inscription", description = "Crée un nouvel utilisateur et retourne un JWT.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inscription réussie"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest registerRequest) {
        User user = userService.register(registerRequest);
        String token = userService.generateJwtToken(user);
        return ResponseEntity.ok(new UserResponse(user, token));
    }

    @Operation(summary = "Connexion", description = "Connecte un utilisateur existant et retourne un JWT.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Connexion réussie"),
            @ApiResponse(responseCode = "401", description = "Identifiants incorrects")
    })
    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.login(loginRequest);
        String token = userService.generateJwtToken(user);
        return ResponseEntity.ok(new UserResponse(user, token));
    }

    @Operation(summary = "Profil utilisateur", description = "Retourne les informations de l'utilisateur connecté.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Requête réussie"),
            @ApiResponse(responseCode = "403", description = "Non autorisé ou token invalide")
    })
    @GetMapping("/me")
    public ResponseEntity<UserResponseMe> me(
            @Parameter(description = "Token JWT au format Bearer")
            @RequestHeader("Authorization") String token) {
        String cleanToken = token.replace("Bearer ", "");
        User user = userService.getUserFromToken(cleanToken);
        return ResponseEntity.ok(new UserResponseMe(user));
    }
}
