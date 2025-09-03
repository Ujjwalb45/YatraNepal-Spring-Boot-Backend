package com.yatranepal.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@EntityListeners(AuditingEntityListener.class)
@Schema(description = "Review entity for rating places, hotels, exchange centers, and tourist guides")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the review", example = "1")
    private Long id;

    @NotNull(message = "User ID is required")
    @Column(name = "user_id", nullable = false)
    @Schema(description = "ID of the user who wrote the review", example = "1")
    private Long userId;

    @NotNull(message = "Reviewed item ID is required")
    @Column(name = "reviewed_item_id", nullable = false)
    @Schema(description = "ID of the item being reviewed", example = "1")
    private Long reviewedItemId;

    @NotNull(message = "Reviewed model type is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "reviewed_model", nullable = false)
    @Schema(description = "Type of entity being reviewed", example = "PLACE")
    private ReviewedModel reviewedModel;

    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    @Column(nullable = false)
    @Schema(description = "Rating given (1-5)", example = "4")
    private Integer rating;

    @Column(columnDefinition = "TEXT")
    @Schema(description = "Review comment", example = "Great place to visit!")
    private String comment;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    @Schema(description = "Review creation timestamp")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    @Schema(description = "Review last update timestamp")
    private LocalDateTime updatedAt;

    // Enum for reviewed model types
    public enum ReviewedModel {
        PLACE, HOTEL, EXCHANGE_CENTER, TOURIST_GUIDE
    }

    // Constructors
    public Review() {}

    public Review(Long userId, Long reviewedItemId, ReviewedModel reviewedModel, Integer rating) {
        this.userId = userId;
        this.reviewedItemId = reviewedItemId;
        this.reviewedModel = reviewedModel;
        this.rating = rating;
    }

    public Review(Long userId, Long reviewedItemId, ReviewedModel reviewedModel, Integer rating, String comment) {
        this.userId = userId;
        this.reviewedItemId = reviewedItemId;
        this.reviewedModel = reviewedModel;
        this.rating = rating;
        this.comment = comment;
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

    public Long getReviewedItemId() {
        return reviewedItemId;
    }

    public void setReviewedItemId(Long reviewedItemId) {
        this.reviewedItemId = reviewedItemId;
    }

    public ReviewedModel getReviewedModel() {
        return reviewedModel;
    }

    public void setReviewedModel(ReviewedModel reviewedModel) {
        this.reviewedModel = reviewedModel;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
