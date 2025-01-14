package org.aliezkan.chatapi.repository;

import org.aliezkan.chatapi.persistance.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    List<MessageEntity> findTop50ByOrderByDateTimeDesc();
}
