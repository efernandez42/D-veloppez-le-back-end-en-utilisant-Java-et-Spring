package com.rental.dto;

import com.rental.model.User;

public class UserResponse {
    private String token;

    public UserResponse(User user, String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
