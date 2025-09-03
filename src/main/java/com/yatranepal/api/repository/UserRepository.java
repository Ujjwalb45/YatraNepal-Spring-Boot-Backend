package com.yatranepal.api.repository;

import com.yatranepal.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByUsername(String username);
    
    Optional<User> findByEmail(String email);
    
    Optional<User> findByUsernameOrEmail(String username, String email);
    
    Optional<User> findByResetPasswordToken(String token);
    
    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
}
