package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    AccountRepository accountRepository;

    // Create a new message. Text must be <255 posted by refers to existing user.
    public Message createNewMessage(Message message) {
        int poster = message.getPosted_by();
        String text = message.getMessage_text();
        if (accountRepository.existsById(poster)) {
            if (text.length() > 0 && text.length() < 255) {
                return messageRepository.save(message);
            }
        }
        return null;
    }
    
    // Get all messages
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    // Get a message by id
    public Message getMessageById(Integer id) {
        return messageRepository.findById(id).orElse(null);
    }

    // Get all messages by a given user
    public List<Message> getMessagesByPoster(Integer userId){
        return messageRepository.getMessagesByUser(userId);
    }

    // Update a message given its id
    public Message updateMessage(Integer id, Message message) {
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if (optionalMessage.isPresent()) {
            String text = message.getMessage_text();
            if (text.length() > 0 && text.length() < 255) {
                Message match = optionalMessage.get();
                match.setMessage_text(text);
                return messageRepository.save(match);
            }
        }
        return null;
    }

    // Delete a message given its id
    public void deleteMessage(Integer id){
        messageRepository.deleteById(id);
    }
}
