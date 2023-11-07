package com.example.controller;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    @Autowired
    AccountService accountService;

    @Autowired
    MessageService messageService;

    // Process new user registration
    @PostMapping("/register")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        String person = account.getUsername();
        List<String> users = accountService.getAllUsernames();
        for (int i = 0; i < users.size(); i++) {
            if (person.equals(users.get(i))) {
                return new ResponseEntity<Account>(HttpStatus.CONFLICT);
            }
        }
        return new ResponseEntity<Account>(accountService.createAccount(account), HttpStatus.OK);
    }
     
    // Process user login
    @PostMapping("/login")
    public ResponseEntity<Account> authAccount(@RequestBody Account account) {
        if (accountService.authAccount(account) != null) {
            return new ResponseEntity<Account>(accountService.authAccount(account), HttpStatus.OK);
        }
        return new ResponseEntity<Account>(HttpStatus.UNAUTHORIZED);
    }

    // Process creation of a new message
    @PostMapping("/messages")
    public ResponseEntity<Message> createNewMessage(@RequestBody Message message) {
        if (messageService.createNewMessage(message) != null) {
            return new ResponseEntity<Message>(messageService.createNewMessage(message), HttpStatus.OK);
        }
        return new ResponseEntity<Message>(HttpStatus.BAD_REQUEST);
    }

    // Get all messages
    @GetMapping("/messages")
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    // Get message by id
    @GetMapping("/messages/{message_id}")
    public Message getMessageById(@PathVariable("message_id") Integer id) {
        return messageService.getMessageById(id);
    }

    // Updates a message given its id and replacement text
    @PatchMapping("/messages/{message_id}")
    public ResponseEntity<String> updateMessage(@PathVariable("message_id") Integer id, @RequestBody Message message){
        if (messageService.getMessageById(id) != null) {
            Message updatedMessage = messageService.updateMessage(id, message);
            if (updatedMessage != null){
                return new ResponseEntity<String>("1", HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    }

    // Deletes a message given its id
    @DeleteMapping("/messages/{message_id}")
    public ResponseEntity<String> deleteMessage(@PathVariable("message_id") Integer id){
        if (messageService.getMessageById(id) != null){
            messageService.deleteMessage(id);
            return new ResponseEntity<String>("1", HttpStatus.OK);
        }
        return null;
    }

    // Get all messages by a specific user given user id
    @GetMapping("/accounts/{account_id}/messages")
    public List<Message> getMessagesByUser(@PathVariable("account_id") Integer userId){
        return messageService.getMessagesByPoster(userId);
    }
}
