package com.yatranepal.api.repository;

import com.yatranepal.api.model.ImageSlider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageSliderRepository extends JpaRepository<ImageSlider, Long> {
    
    List<ImageSlider> findByNameContainingIgnoreCase(String name);
    
    List<ImageSlider> findByImageType(ImageSlider.ImageType imageType);
    
    @Query("SELECT i FROM ImageSlider i ORDER BY i.createdAt DESC")
    List<ImageSlider> findAllOrderByCreatedAtDesc();
}
