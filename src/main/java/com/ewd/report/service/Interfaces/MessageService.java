package com.ewd.report.service.Interfaces;

import com.ewd.report.entity.Message;
import com.ewd.report.entity.User;

import java.util.List;

public interface MessageService {

    Message sendMessage(Message message);

    List<Message> getAllMessages();

    Boolean changeStatusMessage(Message message, Long id);

    List<Message> getMessageReceivedForUser(Long id);


}
