package com.yatranepal.api.repository;

import com.yatranepal.api.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    
    List<Hotel> findByCity(String city);
    
    List<Hotel> findByFeatured(Boolean featured);
    
    List<Hotel> findByType(String type);
    
    @Query("SELECT h FROM Hotel h WHERE h.cheapestPrice >= :minPrice AND h.cheapestPrice <= :maxPrice")
    List<Hotel> findByPriceRange(Double minPrice, Double maxPrice);
    
    List<Hotel> findByNameContainingIgnoreCase(String name);
    
    List<Hotel> findByCityAndType(String city, String type);
}
