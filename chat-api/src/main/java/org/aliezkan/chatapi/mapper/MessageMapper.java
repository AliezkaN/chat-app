package org.aliezkan.chatapi.mapper;

import org.aliezkan.chatapi.domain.Message;
import org.aliezkan.chatapi.persistance.MessageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MessageMapper {
    Message toDomain(MessageEntity entity);
    MessageEntity toEntity(Message domain);
}
