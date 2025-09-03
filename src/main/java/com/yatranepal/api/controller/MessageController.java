package com.yatranepal.api.controller;

import com.yatranepal.api.model.Message;
import com.yatranepal.api.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@Tag(name = "Message Management", description = "APIs for managing chat messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping
    @Operation(summary = "Get all messages", description = "Retrieve all messages")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Messages retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = messageService.getAllMessages();
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get message by ID", description = "Retrieve a specific message by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Message found"),
        @ApiResponse(responseCode = "404", description = "Message not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Message> getMessageById(@PathVariable Long id) {
        Optional<Message> message = messageService.getMessageById(id);
        return message.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create new message", description = "Send a new message")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Message created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Message> createMessage(@Valid @RequestBody Message message) {
        Message createdMessage = messageService.createMessage(message);
        return ResponseEntity.ok(createdMessage);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update message", description = "Update an existing message")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Message updated successfully"),
        @ApiResponse(responseCode = "404", description = "Message not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Message> updateMessage(@PathVariable Long id, @Valid @RequestBody Message message) {
        if (!messageService.getMessageById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Message updatedMessage = messageService.updateMessage(id, message);
        return ResponseEntity.ok(updatedMessage);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete message", description = "Delete a message by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Message deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Message not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        if (!messageService.getMessageById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        messageService.deleteMessage(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/chat/{chatId}")
    @Operation(summary = "Get messages by chat", description = "Retrieve all messages for a specific chat")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Messages retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Message>> getMessagesByChatId(@PathVariable Long chatId) {
        List<Message> messages = messageService.getMessagesByChatId(chatId);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/sender/{senderId}")
    @Operation(summary = "Get messages by sender", description = "Retrieve all messages sent by a specific user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Messages retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Message>> getMessagesBySenderId(@PathVariable Long senderId) {
        List<Message> messages = messageService.getMessagesBySenderId(senderId);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/chat/{chatId}/count")
    @Operation(summary = "Get message count", description = "Get total number of messages in a chat")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Message count retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Long> getMessageCountByChatId(@PathVariable Long chatId) {
        Long count = messageService.getMessageCountByChatId(chatId);
        return ResponseEntity.ok(count);
    }
}
