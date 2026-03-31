package com.password.password_generator.dto;

import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
}