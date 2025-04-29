package com.rental.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageRequest {
    private String message;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("rental_id")
    private Long rentalId;

    // Constructeur par défaut
    public MessageRequest() {}

    // Getters et Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRentalId() {
        return rentalId;
    }

    public void setRentalId(Long rentalId) {
        this.rentalId = rentalId;
    }
}