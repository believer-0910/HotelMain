package com.exadel.demo.repository;

import com.exadel.demo.entity.RabbitMqMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RabbitMqMessageRepository extends JpaRepository<RabbitMqMessage, Long> {

    @Query(value = "SELECT * FROM rabbit_mq_message WHERE status = 'IN_PROGRESS'", nativeQuery = true)
    List<RabbitMqMessage> findAllInProgressMessages();

}
