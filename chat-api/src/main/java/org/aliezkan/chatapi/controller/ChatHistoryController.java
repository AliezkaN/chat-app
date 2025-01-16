package org.aliezkan.chatapi.controller;

import lombok.RequiredArgsConstructor;
import org.aliezkan.chatapi.domain.Message;
import org.aliezkan.chatapi.service.ChatHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/history")
public class ChatHistoryController {
    private final ChatHistoryService service;

    @GetMapping
    public ResponseEntity<List<Message>> getHistory() {
        return ResponseEntity.ok(service.getHistory());
    }
}
