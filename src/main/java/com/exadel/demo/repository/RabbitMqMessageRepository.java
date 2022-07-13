package com.exadel.demo.repository;

import com.exadel.demo.entity.RabbitMqMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RabbitMqMessageRepository extends JpaRepository<RabbitMqMessage, Long> {

}
