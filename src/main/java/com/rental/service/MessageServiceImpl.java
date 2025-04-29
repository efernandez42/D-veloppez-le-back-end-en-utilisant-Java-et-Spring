package com.rental.service;

import com.rental.model.Message;
import com.rental.model.Rental;
import com.rental.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message saveMessage(@RequestBody Message message) {
        return messageRepository.save(message);
    }
}
