package com.etfbl.ipbackend.services.impl;

import com.etfbl.ipbackend.exceptions.ExistingException;
import com.etfbl.ipbackend.models.Message;
import com.etfbl.ipbackend.models.requests.MessageRequest;
import com.etfbl.ipbackend.repositories.MessageRepository;
import com.etfbl.ipbackend.services.MessageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message createMessage(MessageRequest messageRequest) {
        Message message = new Message();
        message.setText(messageRequest.getText());
        message.setStatus(messageRequest.getStatus());
        message.setFkU(messageRequest.getFkU());
        return messageRepository.save(message);
    }

    @Override
    public Optional<Message> getMessageById(Integer id) {
        return messageRepository.findById(id);
    }

    @Override
    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

    @Override
    public Message updateMessage(Integer id, MessageRequest messageRequest) throws ExistingException {
        Optional<Message> messageOptional = messageRepository.findById(id);
        if (messageOptional.isEmpty())
            throw new ExistingException("Kategorija sa ID: " + id + " ne postoji u bazi podataka");

        Message existingMessage = messageOptional.get();
        existingMessage.setFkU(messageRequest.getFkU());
        existingMessage.setText(messageRequest.getText());
        existingMessage.setStatus(messageRequest.getStatus());

        return messageRepository.save(existingMessage);
    }

    @Override
    public void deleteMessage(Integer id) {
        messageRepository.deleteById(id);
    }
}
