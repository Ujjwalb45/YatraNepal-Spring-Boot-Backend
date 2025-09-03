package com.yatranepal.api.controller;

import com.yatranepal.api.model.Chat;
import com.yatranepal.api.service.ChatService;
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
@RequestMapping("/api/chats")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@Tag(name = "Chat Management", description = "APIs for managing chat conversations")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping
    @Operation(summary = "Get all chats", description = "Retrieve all chat conversations")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Chats retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Chat>> getAllChats() {
        List<Chat> chats = chatService.getAllChats();
        return ResponseEntity.ok(chats);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get chat by ID", description = "Retrieve a specific chat by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Chat found"),
        @ApiResponse(responseCode = "404", description = "Chat not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Chat> getChatById(@PathVariable Long id) {
        Optional<Chat> chat = chatService.getChatById(id);
        return chat.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create new chat", description = "Create a new chat conversation")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Chat created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Chat> createChat(@Valid @RequestBody Chat chat) {
        Chat createdChat = chatService.createChat(chat);
        return ResponseEntity.ok(createdChat);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update chat", description = "Update an existing chat")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Chat updated successfully"),
        @ApiResponse(responseCode = "404", description = "Chat not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Chat> updateChat(@PathVariable Long id, @Valid @RequestBody Chat chat) {
        if (!chatService.getChatById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Chat updatedChat = chatService.updateChat(id, chat);
        return ResponseEntity.ok(updatedChat);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete chat", description = "Delete a chat by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Chat deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Chat not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteChat(@PathVariable Long id) {
        if (!chatService.getChatById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        chatService.deleteChat(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get chats by user", description = "Retrieve all chats for a specific user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Chats retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Chat>> getChatsByUserId(@PathVariable Long userId) {
        List<Chat> chats = chatService.getChatsByUserId(userId);
        return ResponseEntity.ok(chats);
    }

    @GetMapping("/between/{userId1}/{userId2}")
    @Operation(summary = "Get chat between users", description = "Retrieve chat conversation between two users")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Chat retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Chat>> getChatBetweenUsers(@PathVariable Long userId1, @PathVariable Long userId2) {
        List<Chat> chats = chatService.getChatBetweenUsers(userId1, userId2);
        return ResponseEntity.ok(chats);
    }
}
