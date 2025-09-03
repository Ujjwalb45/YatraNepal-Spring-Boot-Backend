package com.yatranepal.api.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "places")
@EntityListeners(AuditingEntityListener.class)
@Schema(description = "Place entity representing tourist destinations")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the place", example = "1")
    private Long id;

    @NotBlank(message = "Place name is required")
    @Schema(description = "Place name", example = "Swayambhunath Temple", required = true)
    private String name;

    @NotBlank(message = "Description is required")
    @Schema(description = "Place description", example = "Ancient Buddhist temple with panoramic city views", required = true)
    private String description;

    @NotBlank(message = "Category is required")
    @Pattern(regexp = "Cultural|Natural|Historical|Adventure|Religious|Food Destinations|Photography", 
             message = "Category must be one of: Cultural, Natural, Historical, Adventure, Religious, Food Destinations, Photography")
    @Schema(description = "Place category", example = "Religious", 
            allowableValues = {"Cultural", "Natural", "Historical", "Adventure", "Religious", "Food Destinations", "Photography"}, 
            required = true)
    private String category;

    @NotBlank(message = "City is required")
    @Schema(description = "City where place is located", example = "Kathmandu", required = true)
    private String city;

    @NotBlank(message = "Address is required")
    @Schema(description = "Place address", example = "Swayambhu, Kathmandu", required = true)
    private String address;

    @Embedded
    @Schema(description = "Geographic location of the place")
    private GeoLocation location;

    @NotBlank(message = "Image is required")
    @Schema(description = "Place image URL", example = "https://example.com/swayambhu.jpg", required = true)
    private String img;

    @CreatedDate
    @Schema(description = "Creation timestamp")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Schema(description = "Last modification timestamp")
    private LocalDateTime updatedAt;

    // Inner class for GeoJSON location
    @Embeddable
    @Schema(description = "Geographic location in GeoJSON format")
    public static class GeoLocation {
        @Schema(description = "GeoJSON type", example = "Point", allowableValues = {"Point"})
        private String type = "Point";

        @Schema(description = "Coordinates [longitude, latitude]", example = "[85.2936, 27.7149]")
        private List<Double> coordinates;

        // Constructors
        public GeoLocation() {}

        public GeoLocation(List<Double> coordinates) {
            this.type = "Point";
            this.coordinates = coordinates;
        }

        public GeoLocation(Double longitude, Double latitude) {
            this.type = "Point";
            this.coordinates = List.of(longitude, latitude);
        }

        // Getters and Setters
        public String getType() { return type; }
        public void setType(String type) { this.type = type; }

        public List<Double> getCoordinates() { return coordinates; }
        public void setCoordinates(List<Double> coordinates) { this.coordinates = coordinates; }
    }

    // Constructors
    public Place() {}

    public Place(String name, String description, String category, String city, String address, String img) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.city = city;
        this.address = address;
        this.img = img;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public GeoLocation getLocation() { return location; }
    public void setLocation(GeoLocation location) { this.location = location; }

    public String getImg() { return img; }
    public void setImg(String img) { this.img = img; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
