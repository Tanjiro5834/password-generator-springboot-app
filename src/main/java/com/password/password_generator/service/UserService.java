package com.password.password_generator.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.password.password_generator.dto.LoginRequest;
import com.password.password_generator.dto.LoginResponse;
import com.password.password_generator.dto.RegisterRequest;
import com.password.password_generator.dto.RegisterResponse;
import com.password.password_generator.entity.User;
import com.password.password_generator.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterResponse register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()) != null) {
            throw new RuntimeException("Username already exists");
        }

        if (userRepository.findByEmail(request.getEmail()) != null) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        return toDTO(user);
    }

    public LoginResponse login(LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail());
        if (user == null) {
            throw new RuntimeException("Invalid email or password");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return LoginResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .token("sample-jwt-token")
                .build();
    }

    public RegisterResponse toDTO(User user) {
        RegisterResponse response = new RegisterResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setUsername(user.getUsername());
        response.setMessage("User registered successfully");
        return response;
    }
}
