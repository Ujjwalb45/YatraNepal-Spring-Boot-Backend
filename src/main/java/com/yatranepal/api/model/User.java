package com.yatranepal.api.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@Schema(description = "User entity representing system users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the user", example = "1")
    private Long id;

    @NotBlank(message = "Username is required")
    @Column(unique = true, nullable = false)
    @Schema(description = "Unique username", example = "john_doe", required = true)
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Column(unique = true, nullable = false)
    @Schema(description = "User email address", example = "john@example.com", required = true)
    private String email;

    @NotBlank(message = "Country is required")
    @Schema(description = "User's country", example = "Nepal", required = true)
    private String country;

    @Schema(description = "User profile image URL", example = "https://example.com/profile.jpg")
    private String img;

    @NotBlank(message = "City is required")
    @Schema(description = "User's city", example = "Kathmandu", required = true)
    private String city;

    @NotBlank(message = "Phone is required")
    @Schema(description = "User's phone number", example = "+977-9841234567", required = true)
    private String phone;

    @NotBlank(message = "Password is required")
    @Schema(description = "User password (hashed)", required = true)
    private String password;

    @Schema(description = "Whether user has admin privileges", example = "false")
    private Boolean isAdmin = false;

    @Pattern(regexp = "user|tourist guide", message = "Role must be either 'user' or 'tourist guide'")
    @Schema(description = "User role", example = "user", allowableValues = {"user", "tourist guide"})
    private String role = "user";

    @Schema(description = "Password reset token")
    private String resetPasswordToken;

    @Schema(description = "Password reset token expiration time")
    private LocalDateTime resetPasswordExpires;

    @CreatedDate
    @Schema(description = "Account creation timestamp")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Schema(description = "Last modification timestamp")
    private LocalDateTime updatedAt;

    // Constructors
    public User() {}

    public User(String username, String email, String country, String city, String phone, String password) {
        this.username = username;
        this.email = email;
        this.country = country;
        this.city = city;
        this.phone = phone;
        this.password = password;
    }

    // Password encoding method
    public void encodePassword() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.password = encoder.encode(this.password);
    }

    // Password verification method
    public boolean verifyPassword(String rawPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(rawPassword, this.password);
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getImg() { return img; }
    public void setImg(String img) { this.img = img; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Boolean getIsAdmin() { return isAdmin; }
    public void setIsAdmin(Boolean isAdmin) { this.isAdmin = isAdmin; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getResetPasswordToken() { return resetPasswordToken; }
    public void setResetPasswordToken(String resetPasswordToken) { this.resetPasswordToken = resetPasswordToken; }

    public LocalDateTime getResetPasswordExpires() { return resetPasswordExpires; }
    public void setResetPasswordExpires(LocalDateTime resetPasswordExpires) { this.resetPasswordExpires = resetPasswordExpires; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
