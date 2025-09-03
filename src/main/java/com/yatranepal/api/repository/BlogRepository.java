package com.yatranepal.api.repository;

import com.yatranepal.api.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    
    List<Blog> findByUserId(Long userId);
    
    List<Blog> findByTitleContainingIgnoreCase(String title);
    
    @Query("SELECT b FROM Blog b WHERE b.content LIKE %:keyword% OR b.title LIKE %:keyword%")
    List<Blog> findByContentOrTitleContaining(@Param("keyword") String keyword);
    
    @Query("SELECT b FROM Blog b JOIN b.tags t WHERE t = :tag")
    List<Blog> findByTag(@Param("tag") String tag);
    
    List<Blog> findByNameContainingIgnoreCase(String authorName);
    
    @Query("SELECT b FROM Blog b ORDER BY b.createdAt DESC")
    List<Blog> findAllOrderByCreatedAtDesc();
}
