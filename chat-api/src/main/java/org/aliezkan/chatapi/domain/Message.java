package org.aliezkan.chatapi.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message {

    private String content;
    private String author;
    private Type type;

    public enum Type {JOIN, LEAVE, CHAT}
}
