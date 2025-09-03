package com.yatranepal.api.service;

import com.yatranepal.api.model.Chat;
import com.yatranepal.api.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    public List<Chat> getAllChats() {
        return chatRepository.findAll();
    }

    public Optional<Chat> getChatById(Long id) {
        return chatRepository.findById(id);
    }

    public Chat createChat(Chat chat) {
        return chatRepository.save(chat);
    }

    public Chat updateChat(Long id, Chat chat) {
        chat.setId(id);
        return chatRepository.save(chat);
    }

    public void deleteChat(Long id) {
        chatRepository.deleteById(id);
    }

    public List<Chat> getChatsByUserId(Long userId) {
        return chatRepository.findByMembersContaining(userId);
    }

    public List<Chat> getChatBetweenUsers(Long userId1, Long userId2) {
        return chatRepository.findChatBetweenUsers(userId1, userId2);
    }
}
