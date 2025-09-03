package com.yatranepal.api.repository;

import com.yatranepal.api.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    
    List<Review> findByUserId(Long userId);
    
    List<Review> findByReviewedItemIdAndReviewedModel(Long reviewedItemId, Review.ReviewedModel reviewedModel);
    
    List<Review> findByReviewedModel(Review.ReviewedModel reviewedModel);
    
    List<Review> findByRating(Integer rating);
    
    @Query("SELECT r FROM Review r WHERE r.reviewedItemId = :itemId AND r.reviewedModel = :model ORDER BY r.createdAt DESC")
    List<Review> findByItemAndModelOrderByCreatedAtDesc(@Param("itemId") Long itemId, @Param("model") Review.ReviewedModel model);
    
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.reviewedItemId = :itemId AND r.reviewedModel = :model")
    Double findAverageRatingByItemAndModel(@Param("itemId") Long itemId, @Param("model") Review.ReviewedModel model);
    
    @Query("SELECT COUNT(r) FROM Review r WHERE r.reviewedItemId = :itemId AND r.reviewedModel = :model")
    Long countByItemAndModel(@Param("itemId") Long itemId, @Param("model") Review.ReviewedModel model);
    
    @Query("SELECT r FROM Review r WHERE r.rating >= :minRating AND r.reviewedModel = :model")
    List<Review> findByMinimumRatingAndModel(@Param("minRating") Integer minRating, @Param("model") Review.ReviewedModel model);
}
