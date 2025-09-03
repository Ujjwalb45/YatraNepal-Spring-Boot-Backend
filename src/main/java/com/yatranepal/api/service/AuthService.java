package com.yatranepal.api.service;

import com.yatranepal.api.dto.AuthRequest;
import com.yatranepal.api.dto.AuthResponse;
import com.yatranepal.api.dto.RegisterRequest;
import com.yatranepal.api.model.User;
import com.yatranepal.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    public AuthResponse register(RegisterRequest request) {
        // Check if username or email already exists
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // Create new user
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhone(request.getPhone());
        user.setCity(request.getCity());
        user.setCountry(request.getCountry());
        user.setImg(request.getImg());
        user.setRole(request.getRole());

        // Encode password
        user.encodePassword();

        // Save user
        User savedUser = userRepository.save(user);

        // Generate token
        String token = jwtService.generateToken(savedUser.getId().toString(), savedUser.getUsername(), savedUser.getRole());

        return new AuthResponse(token, savedUser.getId().toString(), savedUser.getUsername(), 
                               savedUser.getEmail(), savedUser.getRole(), savedUser.getIsAdmin());
    }

    public AuthResponse login(AuthRequest request) {
        // Find user by username or email
        Optional<User> userOpt = userRepository.findByUsernameOrEmail(
            request.getUsernameOrEmail(), request.getUsernameOrEmail());

        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = userOpt.get();

        // Verify password
        if (!user.verifyPassword(request.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // Generate token
        String token = jwtService.generateToken(user.getId().toString(), user.getUsername(), user.getRole());

        return new AuthResponse(token, user.getId().toString(), user.getUsername(), 
                               user.getEmail(), user.getRole(), user.getIsAdmin());
    }

    public User getCurrentUser(String token) {
        String userId = jwtService.extractUserId(token);
        return userRepository.findById(Long.parseLong(userId))
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
