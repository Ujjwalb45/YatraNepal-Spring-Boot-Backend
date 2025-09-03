package com.yatranepal.api.repository;

import com.yatranepal.api.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    
    @Query("SELECT c FROM Chat c JOIN c.members m WHERE m = :userId")
    List<Chat> findByMembersContaining(@Param("userId") Long userId);
    
    @Query("SELECT c FROM Chat c WHERE :userId1 MEMBER OF c.members AND :userId2 MEMBER OF c.members")
    List<Chat> findChatBetweenUsers(@Param("userId1") Long userId1, @Param("userId2") Long userId2);
}
