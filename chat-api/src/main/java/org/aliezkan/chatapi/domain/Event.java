package org.aliezkan.chatapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aliezkan.chatapi.domain.enums.EventType;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private EventType type;
    private LocalDateTime dateTime;
    private String initiator;

    public Event(EventType eventType, String initiator) {
        this.type = eventType;
        this.initiator = initiator;
        this.dateTime = LocalDateTime.now();
    }
}
