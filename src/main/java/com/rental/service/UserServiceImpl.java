package com.rental.service;

import com.rental.model.User;
import com.rental.repository.UserRepository;
import com.rental.dto.LoginRequest;
import com.rental.dto.RegisterRequest;
import com.rental.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User register(RegisterRequest registerRequest) {
        User user = new User(registerRequest.getName(), registerRequest.getEmail(),
                passwordEncoder.encode(registerRequest.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getLogin())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return user;
    }


    @Override
    public String generateJwtToken(User user) {
        return jwtUtil.generateToken(user);
    }

    @Override
    public User getUserFromToken(String token) {
        String email = jwtUtil.extractUsername(token);
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
