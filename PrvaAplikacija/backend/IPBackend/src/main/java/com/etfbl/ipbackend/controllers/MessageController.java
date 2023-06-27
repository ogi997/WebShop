package com.etfbl.ipbackend.controllers;

import com.etfbl.ipbackend.models.Message;
import com.etfbl.ipbackend.models.requests.MessageRequest;
import com.etfbl.ipbackend.services.MessageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/messages")
public class MessageController {
    private final MessageService messageService;
    private static final Logger logger = LogManager.getLogger(MessageController.class);

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<Message> createMessage(@RequestBody MessageRequest messageRequest) {
        Message message = messageService.createMessage(messageRequest);
        logger.info("POST Poslana poruka supportu");
        return ResponseEntity.ok(message);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable("id") Integer id) {
        Optional<Message> messageOptional = messageService.getMessageById(id);
        return messageOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = messageService.getMessages();

        return ResponseEntity.ok(messages);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable("id") Integer id, @RequestBody MessageRequest messageRequest) {
        try {
            Message message = messageService.updateMessage(id, messageRequest);

            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable("id") Integer id) {
        messageService.deleteMessage(id);

        return ResponseEntity.noContent().build();
    }
}
