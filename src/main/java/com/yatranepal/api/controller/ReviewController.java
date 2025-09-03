package com.yatranepal.api.controller;

import com.yatranepal.api.model.Review;
import com.yatranepal.api.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@Tag(name = "Review Management", description = "APIs for managing reviews and ratings")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    @Operation(summary = "Get all reviews", description = "Retrieve all reviews")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reviews retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get review by ID", description = "Retrieve a specific review by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Review found"),
        @ApiResponse(responseCode = "404", description = "Review not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        Optional<Review> review = reviewService.getReviewById(id);
        return review.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create new review", description = "Create a new review")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Review created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Review> createReview(@Valid @RequestBody Review review) {
        Review createdReview = reviewService.createReview(review);
        return ResponseEntity.ok(createdReview);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update review", description = "Update an existing review")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Review updated successfully"),
        @ApiResponse(responseCode = "404", description = "Review not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @Valid @RequestBody Review review) {
        if (!reviewService.getReviewById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Review updatedReview = reviewService.updateReview(id, review);
        return ResponseEntity.ok(updatedReview);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete review", description = "Delete a review by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Review deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Review not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        if (!reviewService.getReviewById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        reviewService.deleteReview(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get reviews by user", description = "Retrieve all reviews by a specific user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reviews retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Review>> getReviewsByUserId(@PathVariable Long userId) {
        List<Review> reviews = reviewService.getReviewsByUserId(userId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/{model}/{itemId}")
    @Operation(summary = "Get reviews for item", description = "Retrieve all reviews for a specific item")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reviews retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Review>> getReviewsByItemAndModel(
            @PathVariable Review.ReviewedModel model,
            @PathVariable Long itemId) {
        List<Review> reviews = reviewService.getReviewsByItemAndModel(itemId, model);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/model/{model}")
    @Operation(summary = "Get reviews by model type", description = "Retrieve all reviews for a specific model type")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reviews retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Review>> getReviewsByModel(@PathVariable Review.ReviewedModel model) {
        List<Review> reviews = reviewService.getReviewsByModel(model);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/rating/{rating}")
    @Operation(summary = "Get reviews by rating", description = "Retrieve all reviews with a specific rating")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reviews retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Review>> getReviewsByRating(@PathVariable Integer rating) {
        List<Review> reviews = reviewService.getReviewsByRating(rating);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/{model}/{itemId}/average")
    @Operation(summary = "Get average rating", description = "Get average rating for a specific item")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Average rating retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Double> getAverageRating(
            @PathVariable Review.ReviewedModel model,
            @PathVariable Long itemId) {
        Double averageRating = reviewService.getAverageRating(itemId, model);
        return ResponseEntity.ok(averageRating != null ? averageRating : 0.0);
    }

    @GetMapping("/{model}/{itemId}/count")
    @Operation(summary = "Get review count", description = "Get total number of reviews for a specific item")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Review count retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Long> getReviewCount(
            @PathVariable Review.ReviewedModel model,
            @PathVariable Long itemId) {
        Long count = reviewService.getReviewCount(itemId, model);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/model/{model}/minimum-rating/{minRating}")
    @Operation(summary = "Get reviews by minimum rating", description = "Retrieve reviews with minimum rating for a model type")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reviews retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Review>> getReviewsByMinimumRating(
            @PathVariable Review.ReviewedModel model,
            @PathVariable Integer minRating) {
        List<Review> reviews = reviewService.getReviewsByMinimumRating(minRating, model);
        return ResponseEntity.ok(reviews);
    }
}
