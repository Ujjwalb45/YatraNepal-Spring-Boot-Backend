package com.yatranepal.api.repository;

import com.yatranepal.api.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    
    List<Place> findByCity(String city);
    
    List<Place> findByCategory(String category);
    
    List<Place> findByNameContainingIgnoreCase(String name);
    
    List<Place> findByCityAndCategory(String city, String category);
    
    // For PostgreSQL, we'll use a simplified location query
    @Query("SELECT p FROM Place p WHERE p.location.coordinates IS NOT NULL")
    List<Place> findNearLocation(Double longitude, Double latitude, Double maxDistance);
}
