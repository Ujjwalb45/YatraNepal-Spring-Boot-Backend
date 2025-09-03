package com.yatranepal.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@EntityListeners(AuditingEntityListener.class)
@Schema(description = "Message entity for chat messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the message", example = "1")
    private Long id;

    @NotNull(message = "Chat ID is required")
    @Column(name = "chat_id", nullable = false)
    @Schema(description = "ID of the chat this message belongs to", example = "1")
    private Long chatId;

    @NotNull(message = "Sender ID is required")
    @Column(name = "sender_id", nullable = false)
    @Schema(description = "ID of the user who sent the message", example = "1")
    private Long senderId;

    @Column(columnDefinition = "TEXT")
    @Schema(description = "Text content of the message", example = "Hello, how are you?")
    private String text;

    @Column(name = "file_url")
    @Schema(description = "URL of attached file", example = "https://example.com/file.jpg")
    private String fileUrl;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    @Schema(description = "Message creation timestamp")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    @Schema(description = "Message last update timestamp")
    private LocalDateTime updatedAt;

    // Constructors
    public Message() {}

    public Message(Long chatId, Long senderId, String text) {
        this.chatId = chatId;
        this.senderId = senderId;
        this.text = text;
    }

    public Message(Long chatId, Long senderId, String text, String fileUrl) {
        this.chatId = chatId;
        this.senderId = senderId;
        this.text = text;
        this.fileUrl = fileUrl;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
