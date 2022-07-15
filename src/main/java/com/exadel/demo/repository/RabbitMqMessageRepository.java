package com.exadel.demo.repository;

import com.exadel.demo.entity.RabbitMqMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RabbitMqMessageRepository extends JpaRepository<RabbitMqMessage, Long> {

    List<RabbitMqMessage> findByStatus(String status);

}
