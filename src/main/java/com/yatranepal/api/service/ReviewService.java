package com.yatranepal.api.service;

import com.yatranepal.api.model.Review;
import com.yatranepal.api.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    public Review updateReview(Long id, Review review) {
        review.setId(id);
        return reviewRepository.save(review);
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    public List<Review> getReviewsByUserId(Long userId) {
        return reviewRepository.findByUserId(userId);
    }

    public List<Review> getReviewsByItemAndModel(Long itemId, Review.ReviewedModel model) {
        return reviewRepository.findByItemAndModelOrderByCreatedAtDesc(itemId, model);
    }

    public List<Review> getReviewsByModel(Review.ReviewedModel model) {
        return reviewRepository.findByReviewedModel(model);
    }

    public List<Review> getReviewsByRating(Integer rating) {
        return reviewRepository.findByRating(rating);
    }

    public Double getAverageRating(Long itemId, Review.ReviewedModel model) {
        return reviewRepository.findAverageRatingByItemAndModel(itemId, model);
    }

    public Long getReviewCount(Long itemId, Review.ReviewedModel model) {
        return reviewRepository.countByItemAndModel(itemId, model);
    }

    public List<Review> getReviewsByMinimumRating(Integer minRating, Review.ReviewedModel model) {
        return reviewRepository.findByMinimumRatingAndModel(minRating, model);
    }
}
