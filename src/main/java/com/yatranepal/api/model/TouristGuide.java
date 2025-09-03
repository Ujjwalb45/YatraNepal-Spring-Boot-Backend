package com.yatranepal.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tourist_guides")
@EntityListeners(AuditingEntityListener.class)
@Schema(description = "Tourist guide entity for managing tour guides")
public class TouristGuide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the tourist guide", example = "1")
    private Long id;

    @NotNull(message = "User ID is required")
    @Column(name = "user_id", nullable = false, unique = true)
    @Schema(description = "ID of the user account", example = "1")
    private Long userId;

    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    @Schema(description = "Name of the tourist guide", example = "John Doe")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(nullable = false, unique = true)
    @Schema(description = "Email address of the tourist guide", example = "john@example.com")
    private String email;

    @Column(name = "image_url")
    @Schema(description = "Profile image URL", example = "https://example.com/image.jpg")
    private String img;

    @NotBlank(message = "Location is required")
    @Column(nullable = false)
    @Schema(description = "Location where the guide operates", example = "Kathmandu")
    private String location;

    @NotBlank(message = "Language is required")
    @Column(nullable = false)
    @Schema(description = "Languages spoken by the guide", example = "English, Nepali")
    private String language;

    @NotNull(message = "Experience is required")
    @Min(value = 0, message = "Experience cannot be negative")
    @Column(nullable = false)
    @Schema(description = "Years of experience", example = "5")
    private Integer experience;

    @NotBlank(message = "Contact number is required")
    @Column(name = "contact_number", nullable = false)
    @Schema(description = "Contact phone number", example = "+977-9841234567")
    private String contactNumber;

    @NotBlank(message = "Availability is required")
    @Column(nullable = false)
    @Schema(description = "Availability schedule", example = "Full-time")
    private String availability;

    @NotBlank(message = "License number is required")
    @Pattern(regexp = "^[A-Z0-9]+$", message = "Invalid license number format")
    @Column(name = "license_number", nullable = false)
    @Schema(description = "Guide license number", example = "TG12345")
    private String licenseNumber;

    @ElementCollection
    @CollectionTable(name = "tourist_guide_categories", joinColumns = @JoinColumn(name = "guide_id"))
    @Column(name = "category")
    @Schema(description = "Categories of tours the guide specializes in")
    private List<String> category;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    @Schema(description = "Guide registration timestamp")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    @Schema(description = "Guide last update timestamp")
    private LocalDateTime updatedAt;

    // Constructors
    public TouristGuide() {}

    public TouristGuide(Long userId, String name, String email, String location, String language, Integer experience, String contactNumber, String availability, String licenseNumber) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.location = location;
        this.language = language;
        this.experience = experience;
        this.contactNumber = contactNumber;
        this.availability = availability;
        this.licenseNumber = licenseNumber;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
