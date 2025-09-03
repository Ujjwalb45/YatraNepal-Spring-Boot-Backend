package com.yatranepal.api.repository;

import com.yatranepal.api.model.ChadParba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChadParbaRepository extends JpaRepository<ChadParba, Long> {
    
    List<ChadParba> findByNepaliMonth(ChadParba.NepaliMonth nepaliMonth);
    
    List<ChadParba> findByCategory(String category);
    
    List<ChadParba> findByTitleContainingIgnoreCase(String title);
    
    List<ChadParba> findByCategoryContainingIgnoreCase(String category);
    
    @Query("SELECT c FROM ChadParba c WHERE c.nepaliMonth = :month AND c.nepaliDay = :day")
    List<ChadParba> findByNepaliMonthAndDay(@Param("month") ChadParba.NepaliMonth month, @Param("day") Integer day);
    
    @Query("SELECT c FROM ChadParba c ORDER BY c.nepaliMonth, c.nepaliDay")
    List<ChadParba> findAllOrderByDate();
}
