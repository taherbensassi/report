package com.ewd.report.controller;


import com.ewd.report.entity.FoundItem;
import com.ewd.report.entity.Message;
import com.ewd.report.entity.User;
import com.ewd.report.service.Interfaces.MessageService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MessageController {


    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/message/send")
    public Message sendMessage(@RequestBody Message message){
        return messageService.sendMessage(message);
    }

    @GetMapping("/messages")
    public List<Message> getAllMessages(){
        return messageService.getAllMessages();
    }

    @PutMapping("/message-status/{id}")
    public boolean changeStatusMessage(@Valid @RequestBody Message message, @PathVariable("id") Long id){
        return messageService.changeStatusMessage(message, id);
    }

    @GetMapping("/message-user/{id}")
    public List<Message> getAllMessagesForUser(@PathVariable("id") Long id){
        return messageService.getMessageReceivedForUser(id);
    }




}
