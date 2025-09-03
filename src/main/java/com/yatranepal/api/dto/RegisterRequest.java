package com.yatranepal.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "User registration request DTO")
public class RegisterRequest {

    @NotBlank(message = "Username is required")
    @Schema(description = "Unique username", example = "john_doe", required = true)
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Schema(description = "User email address", example = "john@example.com", required = true)
    private String email;

    @NotBlank(message = "Password is required")
    @Schema(description = "User password", example = "password123", required = true)
    private String password;

    @NotBlank(message = "Phone is required")
    @Schema(description = "User's phone number", example = "+977-9841234567", required = true)
    private String phone;

    @NotBlank(message = "City is required")
    @Schema(description = "User's city", example = "Kathmandu", required = true)
    private String city;

    @NotBlank(message = "Country is required")
    @Schema(description = "User's country", example = "Nepal", required = true)
    private String country;

    @Schema(description = "User profile image URL", example = "https://example.com/profile.jpg")
    private String img;

    @Pattern(regexp = "user|tourist guide", message = "Role must be either 'user' or 'tourist guide'")
    @Schema(description = "User role", example = "user", allowableValues = {"user", "tourist guide"})
    private String role = "user";

    // Constructors
    public RegisterRequest() {}

    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getImg() { return img; }
    public void setImg(String img) { this.img = img; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
