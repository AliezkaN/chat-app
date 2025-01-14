package org.aliezkan.chatapi.service;

import org.aliezkan.chatapi.domain.Event;
import org.aliezkan.chatapi.domain.Message;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

import java.util.List;

public interface ChatService {
    Message send(Message message);
    Event connect(Event chatEvent, SimpMessageHeaderAccessor headerAccessor);
    List<Message> getHistory();
}
