package com.yatranepal.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Pattern;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "exchange_centers")
@EntityListeners(AuditingEntityListener.class)
@Schema(description = "Exchange center entity for currency exchange services")
public class ExchangeCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the exchange center", example = "1")
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    @Column(nullable = false, length = 100)
    @Schema(description = "Name of the exchange center", example = "Nepal Exchange Center")
    private String name;

    @NotBlank(message = "Address is required")
    @Size(max = 200, message = "Address cannot exceed 200 characters")
    @Column(nullable = false, length = 200)
    @Schema(description = "Address of the exchange center", example = "Thamel, Kathmandu")
    private String address;

    @NotNull(message = "Latitude is required")
    @DecimalMin(value = "-90.0", message = "Latitude must be between -90 and 90")
    @DecimalMax(value = "90.0", message = "Latitude must be between -90 and 90")
    @Column(nullable = false, precision = 10, scale = 8)
    @Schema(description = "Latitude coordinate", example = "27.7172")
    private BigDecimal lat;

    @NotNull(message = "Longitude is required")
    @DecimalMin(value = "-180.0", message = "Longitude must be between -180 and 180")
    @DecimalMax(value = "180.0", message = "Longitude must be between -180 and 180")
    @Column(nullable = false, precision = 11, scale = 8)
    @Schema(description = "Longitude coordinate", example = "85.3240")
    private BigDecimal lng;

    @ElementCollection
    @CollectionTable(name = "exchange_center_images", joinColumns = @JoinColumn(name = "exchange_center_id"))
    @Column(name = "image_url")
    @Size(max = 3, message = "You can upload up to 3 images only")
    @Schema(description = "List of image URLs (max 3)")
    private List<String> images;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    @Column(length = 500)
    @Schema(description = "Description of the exchange center", example = "Best rates for currency exchange")
    private String description;

    @NotBlank(message = "Contact number is required")
    @Size(max = 15, message = "Contact number cannot exceed 15 characters")
    @Pattern(regexp = "^[0-9+\\-\\s()]*$", message = "Invalid contact number format")
    @Column(name = "contact_number", nullable = false, length = 15)
    @Schema(description = "Contact phone number", example = "+977-1-4444444")
    private String contactNumber;

    @NotBlank(message = "Operating hours are required")
    @Size(max = 50, message = "Hours cannot exceed 50 characters")
    @Column(nullable = false, length = 50)
    @Schema(description = "Operating hours", example = "9:00 AM - 6:00 PM")
    private String hours;

    @NotBlank(message = "Services are required")
    @Size(max = 300, message = "Services cannot exceed 300 characters")
    @Column(nullable = false, length = 300)
    @Schema(description = "Services offered", example = "USD, EUR, GBP exchange")
    private String services;

    @NotNull(message = "Owner ID is required")
    @Column(name = "owner_id", nullable = false)
    @Schema(description = "ID of the owner user", example = "1")
    private Long ownerId;

    @Column(name = "is_active")
    @Schema(description = "Whether the exchange center is active", example = "true")
    private Boolean isActive = true;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    @Schema(description = "Exchange center creation timestamp")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    @Schema(description = "Exchange center last update timestamp")
    private LocalDateTime updatedAt;

    // Constructors
    public ExchangeCenter() {}

    public ExchangeCenter(String name, String address, BigDecimal lat, BigDecimal lng, String contactNumber, String hours, String services, Long ownerId) {
        this.name = name;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
        this.contactNumber = contactNumber;
        this.hours = hours;
        this.services = services;
        this.ownerId = ownerId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
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
