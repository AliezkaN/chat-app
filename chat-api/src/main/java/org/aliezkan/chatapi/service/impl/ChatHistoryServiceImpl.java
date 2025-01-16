package org.aliezkan.chatapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.aliezkan.chatapi.domain.Message;
import org.aliezkan.chatapi.mapper.MessageMapper;
import org.aliezkan.chatapi.repository.MessageRepository;
import org.aliezkan.chatapi.service.ChatHistoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatHistoryServiceImpl implements ChatHistoryService {
    private final MessageRepository repository;
    private final MessageMapper mapper;

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ)
    public List<Message> getHistory() {
        return repository.findTop50ByOrderByDateTimeDesc()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
