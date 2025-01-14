package org.aliezkan.chatapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.aliezkan.chatapi.domain.Event;
import org.aliezkan.chatapi.domain.Message;
import org.aliezkan.chatapi.mapper.MessageMapper;
import org.aliezkan.chatapi.persistance.MessageEntity;
import org.aliezkan.chatapi.repository.MessageRepository;
import org.aliezkan.chatapi.service.ChatService;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final MessageRepository repository;
    private final MessageMapper mapper;

    @Override
    public Message send(Message message) {
        message.setDateTime(LocalDateTime.now());
        MessageEntity persisted = repository.save(mapper.toEntity(message));
        return mapper.toDomain(persisted);
    }

    @Override
    public Event connect(Event event, SimpMessageHeaderAccessor headerAccessor) {
        Optional.ofNullable(headerAccessor.getSessionAttributes())
                .ifPresent(x -> x.put("username", event.getInitiator()));
        event.setDateTime(LocalDateTime.now());
        return event;
    }

    @Override
    public List<Message> getHistory() {
        return repository.findTop50ByOrderByDateTimeDesc()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
