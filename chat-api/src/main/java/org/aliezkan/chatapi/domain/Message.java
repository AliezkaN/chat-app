package org.aliezkan.chatapi.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Message extends Event {
    private String content;
}
