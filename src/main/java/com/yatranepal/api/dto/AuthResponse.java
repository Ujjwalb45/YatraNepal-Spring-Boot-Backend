package com.yatranepal.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Authentication response DTO")
public class AuthResponse {

    @Schema(description = "JWT access token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;

    @Schema(description = "User ID", example = "507f1f77bcf86cd799439011")
    private String userId;

    @Schema(description = "Username", example = "john_doe")
    private String username;

    @Schema(description = "User email", example = "john@example.com")
    private String email;

    @Schema(description = "User role", example = "user")
    private String role;

    @Schema(description = "Whether user is admin", example = "false")
    private Boolean isAdmin;

    // Constructors
    public AuthResponse() {}

    public AuthResponse(String token, String userId, String username, String email, String role, Boolean isAdmin) {
        this.token = token;
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.role = role;
        this.isAdmin = isAdmin;
    }

    // Getters and Setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Boolean getIsAdmin() { return isAdmin; }
    public void setIsAdmin(Boolean isAdmin) { this.isAdmin = isAdmin; }
}
