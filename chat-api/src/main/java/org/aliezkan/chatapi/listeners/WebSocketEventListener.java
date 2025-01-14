package org.aliezkan.chatapi.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aliezkan.chatapi.domain.Event;
import org.aliezkan.chatapi.domain.enums.EventType;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        Optional<String> usernameOpt = Optional.ofNullable(headerAccessor.getSessionAttributes())
                .map(x -> (String) x.get("username"));
        usernameOpt.ifPresent(username -> {
            log.info("user disconnected: {}", username);
            Event message = new Event(EventType.LEAVE, username);
            messagingTemplate.convertAndSend("/topic/public", message);
        });
    }
}
