package com.rental.controller;

import com.rental.dto.MessageRequest;
import com.rental.model.Message;
import com.rental.model.Rental;
import com.rental.model.User;
import com.rental.service.MessageServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/messages")
@Tag(name = "Messages", description = "API de gestion des messages")
public class MessageController {

    @Autowired
    private MessageServiceImpl messageServiceImpl;

    @Operation(
            summary = "Envoyer un message",
            description = "Crée et envoie un nouveau message pour une location"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Message envoyé avec succès",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(type = "object", example = "{\"message\": \"Message send with success\"}")
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Requête invalide",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(type = "object", example = "{\"error\": \"Message d'erreur\"}")
                    )
            )
    })
    @PostMapping
    public ResponseEntity<Map<String, String>> sendMessage(
            @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Détails du message à envoyer",
                    required = true,
                    content = @Content(schema = @Schema(implementation = MessageRequest.class))
            )
            MessageRequest messageRequest
    ) {
        try {
            Message message = new Message();
            message.setMessage(messageRequest.getMessage());

            User user = new User();
            user.setId(messageRequest.getUserId());
            message.setUser(user);

            Rental rental = new Rental();
            rental.setId(messageRequest.getRentalId());
            message.setRental(rental);

            messageServiceImpl.saveMessage(message);
            return ResponseEntity.ok(Map.of("message", "Message send with success"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
