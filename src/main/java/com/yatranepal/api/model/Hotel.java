package com.yatranepal.api.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "hotels")
@EntityListeners(AuditingEntityListener.class)
@Schema(description = "Hotel entity representing accommodation providers")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the hotel", example = "1")
    private Long id;

    @NotBlank(message = "Hotel name is required")
    @Schema(description = "Hotel name", example = "Hotel Everest View", required = true)
    private String name;

    @NotBlank(message = "Hotel type is required")
    @Schema(description = "Type of accommodation", example = "Hotel", required = true)
    private String type;

    @NotBlank(message = "City is required")
    @Schema(description = "City where hotel is located", example = "Kathmandu", required = true)
    private String city;

    @NotBlank(message = "Address is required")
    @Schema(description = "Hotel address", example = "Thamel, Kathmandu", required = true)
    private String address;

    @NotBlank(message = "Distance is required")
    @Schema(description = "Distance from city center", example = "2km from center", required = true)
    private String distance;

    @Schema(description = "Hotel photo URLs", example = "[\"photo1.jpg\", \"photo2.jpg\"]")
    private List<String> photos;

    @NotBlank(message = "Title is required")
    @Schema(description = "Hotel title/tagline", example = "Luxury stay in the heart of Kathmandu", required = true)
    private String title;

    @NotBlank(message = "Description is required")
    @Schema(description = "Hotel description", example = "A beautiful hotel with modern amenities", required = true)
    private String desc;

    @Min(value = 0, message = "Rating must be at least 0")
    @Max(value = 5, message = "Rating must be at most 5")
    @Schema(description = "Hotel rating", example = "4.5", minimum = "0", maximum = "5")
    private Double rating;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Schema(description = "List of rooms")
    private List<Room> rooms;

    @NotNull(message = "Cheapest price is required")
    @Min(value = 0, message = "Price must be positive")
    @Schema(description = "Cheapest room price", example = "2500", required = true)
    private Double cheapestPrice;

    @Schema(description = "Whether hotel is featured", example = "false")
    private Boolean featured = false;

    @CreatedDate
    @Schema(description = "Creation timestamp")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Schema(description = "Last modification timestamp")
    private LocalDateTime updatedAt;

    // Constructors
    public Hotel() {
    }

    public Hotel(String name, String type, String city, String address, String distance,
            String title, String desc, Double cheapestPrice) {
        this.name = name;
        this.type = type;
        this.city = city;
        this.address = address;
        this.distance = distance;
        this.title = title;
        this.desc = desc;
        this.cheapestPrice = cheapestPrice;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Double getCheapestPrice() {
        return cheapestPrice;
    }

    public void setCheapestPrice(Double cheapestPrice) {
        this.cheapestPrice = cheapestPrice;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
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
