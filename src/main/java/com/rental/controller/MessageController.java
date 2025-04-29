package com.rental.controller;

import com.rental.dto.MessageRequest;
import com.rental.model.Message;
import com.rental.model.Rental;
import com.rental.model.User;
import com.rental.service.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;



@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageServiceImpl messageServiceImpl;

    @PostMapping
    public ResponseEntity<Map<String, String>> sendMessage(@RequestBody MessageRequest messageRequest) {
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
