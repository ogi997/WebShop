package com.etfbl.ipbackend.services;

import com.etfbl.ipbackend.exceptions.ExistingException;
import com.etfbl.ipbackend.models.Message;
import com.etfbl.ipbackend.models.requests.MessageRequest;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    Message createMessage(MessageRequest messageRequest);

    Optional<Message> getMessageById(Integer id);

    List<Message> getMessages();

    Message updateMessage(Integer id, MessageRequest messageRequest) throws ExistingException;

    void deleteMessage(Integer id);
}
