package org.aliezkan.chatapi.domain;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

@Data
@EqualsAndHashCode(callSuper = true)
public class Message extends Event {
    @NotEmpty
    @Length(max = 200)
    private String content;
}
