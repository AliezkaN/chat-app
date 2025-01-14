package org.aliezkan.chatapi.persistance;

import jakarta.persistence.*;
import lombok.*;
import org.aliezkan.chatapi.domain.enums.EventType;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "messages")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String initiator;
    private String content;

    @Enumerated(EnumType.STRING)
    private EventType type;

    private LocalDateTime dateTime;
}
