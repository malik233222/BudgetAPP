package com.example.budgetapp.service;

import com.example.budgetapp.dto.request.LoginRequestDto;
import com.example.budgetapp.dto.request.RegisterRequestDto;
import com.example.budgetapp.dto.response.AuthResponseDto;
import com.example.budgetapp.entity.Role;
import com.example.budgetapp.entity.User;
import com.example.budgetapp.repository.RoleRepository;
import com.example.budgetapp.repository.UserRepository;
import com.example.budgetapp.utils.JwtUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    public AuthResponseDto register(RegisterRequestDto request) {
        Optional<User> existingUser = userRepository.findByUsername(request.getUsername());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // İstifadəçiyə ROLE_USER əlavə edilir
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Default role not found"));
        user.getRoles().add(userRole);

        userRepository.save(user);

        String token = jwtUtils.generateToken(user.getUsername());
        return new AuthResponseDto(token, "Registration successful");
    }


    public AuthResponseDto login(LoginRequestDto request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        String token = jwtUtils.generateToken(user.getUsername());
        return new AuthResponseDto(token, "Login successful");
    }
}
