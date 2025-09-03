package com.yatranepal.api.repository;

import com.yatranepal.api.model.ExchangeCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ExchangeCenterRepository extends JpaRepository<ExchangeCenter, Long> {
    
    List<ExchangeCenter> findByOwnerId(Long ownerId);
    
    List<ExchangeCenter> findByIsActive(Boolean isActive);
    
    List<ExchangeCenter> findByNameContainingIgnoreCase(String name);
    
    List<ExchangeCenter> findByAddressContainingIgnoreCase(String address);
    
    List<ExchangeCenter> findByServicesContainingIgnoreCase(String service);
    
    @Query("SELECT ec FROM ExchangeCenter ec WHERE ec.isActive = true")
    List<ExchangeCenter> findActiveExchangeCenters();
    
    @Query("SELECT ec FROM ExchangeCenter ec WHERE " +
           "6371 * acos(cos(radians(:lat)) * cos(radians(CAST(ec.lat AS double))) * " +
           "cos(radians(CAST(ec.lng AS double)) - radians(:lng)) + " +
           "sin(radians(:lat)) * sin(radians(CAST(ec.lat AS double)))) <= :radius")
    List<ExchangeCenter> findByLocationWithinRadius(@Param("lat") BigDecimal lat, 
                                                   @Param("lng") BigDecimal lng, 
                                                   @Param("radius") Double radius);
}
