package com.ewd.report.service.implementations;


import com.ewd.report.entity.Message;
import com.ewd.report.entity.User;
import com.ewd.report.exception.ResourceNotFoundException;
import com.ewd.report.repository.MessageRepository;
import com.ewd.report.repository.UserRepository;
import com.ewd.report.service.Interfaces.MessageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private MessageRepository messageRepository;
    private UserRepository userRepository;

    public MessageServiceImpl(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Message sendMessage(Message message) {
        message.setOpened(0);
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public Boolean changeStatusMessage(Message messageData, Long id) {
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Message", "id", id));
        message.setOpened(1);
        messageRepository.save(message);
        return true;
    }

    @Override
    public List<Message> getMessageReceivedForUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return messageRepository.getMessageReceived(user);
    }


}
