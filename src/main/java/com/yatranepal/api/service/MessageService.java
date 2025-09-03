package com.yatranepal.api.service;

import com.yatranepal.api.model.Message;
import com.yatranepal.api.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Optional<Message> getMessageById(Long id) {
        return messageRepository.findById(id);
    }

    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    public Message updateMessage(Long id, Message message) {
        message.setId(id);
        return messageRepository.save(message);
    }

    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }

    public List<Message> getMessagesByChatId(Long chatId) {
        return messageRepository.findByChatIdOrderByCreatedAtAsc(chatId);
    }

    public List<Message> getMessagesBySenderId(Long senderId) {
        return messageRepository.findBySenderIdOrderByCreatedAtDesc(senderId);
    }

    public Long getMessageCountByChatId(Long chatId) {
        return messageRepository.countByChatId(chatId);
    }
}
