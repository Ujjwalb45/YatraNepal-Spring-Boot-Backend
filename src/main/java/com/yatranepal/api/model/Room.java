package com.yatranepal.api.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Date;

@Entity
@Table(name = "rooms")
@EntityListeners(AuditingEntityListener.class)
@Schema(description = "Room entity representing hotel rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the room", example = "1")
    private Long id;

    @NotBlank(message = "Room title is required")
    @Schema(description = "Room title", example = "Deluxe Double Room", required = true)
    private String title;

    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price must be positive")
    @Schema(description = "Room price per night", example = "3500", required = true)
    private Double price;

    @NotNull(message = "Max people is required")
    @Min(value = 1, message = "Max people must be at least 1")
    @Schema(description = "Maximum number of people", example = "2", required = true)
    private Integer maxPeople;

    @NotBlank(message = "Description is required")
    @Schema(description = "Room description", example = "Spacious room with mountain view", required = true)
    private String desc;

    @ElementCollection
    @CollectionTable(name = "room_numbers", joinColumns = @JoinColumn(name = "room_id"))
    @Schema(description = "Room numbers and availability")
    private List<RoomNumber> roomNumbers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", nullable = false)
    @NotNull(message = "Hotel reference is required")
    @Schema(description = "Reference to the hotel", required = true)
    private Hotel hotel;

    @CreatedDate
    @Schema(description = "Creation timestamp")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Schema(description = "Last modification timestamp")
    private LocalDateTime updatedAt;

    // Inner class for room numbers
    @Embeddable
    @Schema(description = "Room number with availability information")
    public static class RoomNumber {
        @Schema(description = "Room number", example = "101")
        private Integer number;

        @Schema(description = "Dates when room is unavailable")
        private List<Date> unavailableDates;

        // Constructors
        public RoomNumber() {}

        public RoomNumber(Integer number, List<Date> unavailableDates) {
            this.number = number;
            this.unavailableDates = unavailableDates;
        }

        // Getters and Setters
        public Integer getNumber() { return number; }
        public void setNumber(Integer number) { this.number = number; }

        public List<Date> getUnavailableDates() { return unavailableDates; }
        public void setUnavailableDates(List<Date> unavailableDates) { this.unavailableDates = unavailableDates; }
    }

    // Constructors
    public Room() {}

    public Room(String title, Double price, Integer maxPeople, String desc, Hotel hotel) {
        this.title = title;
        this.price = price;
        this.maxPeople = maxPeople;
        this.desc = desc;
        this.hotel = hotel;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Integer getMaxPeople() { return maxPeople; }
    public void setMaxPeople(Integer maxPeople) { this.maxPeople = maxPeople; }

    public String getDesc() { return desc; }
    public void setDesc(String desc) { this.desc = desc; }

    public List<RoomNumber> getRoomNumbers() { return roomNumbers; }
    public void setRoomNumbers(List<RoomNumber> roomNumbers) { this.roomNumbers = roomNumbers; }

    public Hotel getHotel() { return hotel; }
    public void setHotel(Hotel hotel) { this.hotel = hotel; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
