package com.example.demo.User;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class AuthRequest {
    @NotNull(message = "Username is required")
    String username;
    @NotNull(message = "Password is required")
    @Min(value = 12, message = "Password must be longer than 12 characters")
    String password;
}
