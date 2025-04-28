package com.rental.service;

import com.rental.model.User;
import com.rental.dto.LoginRequest;
import com.rental.dto.RegisterRequest;

public interface UserService {
    User register(RegisterRequest registerRequest);
    User login(LoginRequest loginRequest);
    String generateJwtToken(User user);
    User getUserFromToken(String token);
}
