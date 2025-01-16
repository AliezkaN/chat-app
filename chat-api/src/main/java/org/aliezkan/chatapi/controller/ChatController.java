package org.aliezkan.chatapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.aliezkan.chatapi.domain.Event;
import org.aliezkan.chatapi.domain.Message;
import org.aliezkan.chatapi.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private static final String DESTINATION = "/topic/public";
    private static final String MESSAGE_MAPPING_PREFIX = "/chat.";

    private final ChatService service;

    @MessageMapping(MESSAGE_MAPPING_PREFIX + "sendMessage")
    @SendTo(DESTINATION)
    public Message sendMessage(@Payload @Valid Message chatMessage) {
        return service.send(chatMessage);
    }

    @MessageMapping(MESSAGE_MAPPING_PREFIX + "connect")
    @SendTo(DESTINATION)
    public Event connect(@Payload Event chatEvent,
                         SimpMessageHeaderAccessor headerAccessor) {
        return service.connect(chatEvent, headerAccessor);
    }


}
