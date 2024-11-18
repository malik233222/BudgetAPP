package com.example.budgetapp.controller;

import com.example.budgetapp.dto.request.LoginRequestDto;
import com.example.budgetapp.dto.request.RegisterRequestDto;
import com.example.budgetapp.dto.response.AuthResponseDto;
import com.example.budgetapp.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Register a new user.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDto request) {
        try {
            AuthResponseDto response = authService.register(request);
            return ResponseEntity.ok(response); // Return success response with AuthResponseDto
        } catch (RuntimeException e) { // More specific exception handling
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "message", "Registration failed",
                    "error", e.getMessage()
            ));
        } catch (Exception e) { // Catch-all for unexpected errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "message", "Unexpected error during registration",
                    "error", e.getMessage()
            ));
        }
    }

    /**
     * Log in an existing user.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto request) {
        try {
            AuthResponseDto response = authService.login(request);
            return ResponseEntity.ok(response); // Return success response with AuthResponseDto
        } catch (IllegalArgumentException ex) { // For invalid credentials or bad input
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "message", "Unauthorized",
                    "error", ex.getMessage()
            ));
        } catch (RuntimeException ex) { // Handle application-specific errors
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "message", "Login failed",
                    "error", ex.getMessage()
            ));
        } catch (Exception ex) { // Catch-all for unexpected errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "message", "Unexpected error during login",
                    "error", ex.getMessage()
            ));
        }
    }
}
