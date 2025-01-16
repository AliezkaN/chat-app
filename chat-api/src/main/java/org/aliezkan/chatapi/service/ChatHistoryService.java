package org.aliezkan.chatapi.service;

import org.aliezkan.chatapi.domain.Message;

import java.util.List;

public interface ChatHistoryService {
    List<Message> getHistory();
}
