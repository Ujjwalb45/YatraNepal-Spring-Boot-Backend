package com.yatranepal.api.repository;

import com.yatranepal.api.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    
    List<Message> findByChatId(Long chatId);
    
    List<Message> findBySenderId(Long senderId);
    
    @Query("SELECT m FROM Message m WHERE m.chatId = :chatId ORDER BY m.createdAt ASC")
    List<Message> findByChatIdOrderByCreatedAtAsc(@Param("chatId") Long chatId);
    
    @Query("SELECT m FROM Message m WHERE m.chatId = :chatId ORDER BY m.createdAt DESC")
    List<Message> findByChatIdOrderByCreatedAtDesc(@Param("chatId") Long chatId);
    
    @Query("SELECT m FROM Message m WHERE m.senderId = :senderId ORDER BY m.createdAt DESC")
    List<Message> findBySenderIdOrderByCreatedAtDesc(@Param("senderId") Long senderId);
    
    @Query("SELECT COUNT(m) FROM Message m WHERE m.chatId = :chatId")
    Long countByChatId(@Param("chatId") Long chatId);
}
