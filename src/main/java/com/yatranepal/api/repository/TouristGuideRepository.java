package com.yatranepal.api.repository;

import com.yatranepal.api.model.TouristGuide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TouristGuideRepository extends JpaRepository<TouristGuide, Long> {
    
    Optional<TouristGuide> findByUserId(Long userId);
    
    Optional<TouristGuide> findByEmail(String email);
    
    Optional<TouristGuide> findByLicenseNumber(String licenseNumber);
    
    List<TouristGuide> findByLocation(String location);
    
    List<TouristGuide> findByLocationContainingIgnoreCase(String location);
    
    List<TouristGuide> findByLanguageContainingIgnoreCase(String language);
    
    List<TouristGuide> findByAvailability(String availability);
    
    @Query("SELECT tg FROM TouristGuide tg WHERE tg.experience >= :minExperience")
    List<TouristGuide> findByMinimumExperience(@Param("minExperience") Integer minExperience);
    
    @Query("SELECT tg FROM TouristGuide tg JOIN tg.category c WHERE c = :category")
    List<TouristGuide> findByCategory(@Param("category") String category);
    
    List<TouristGuide> findByNameContainingIgnoreCase(String name);
}
