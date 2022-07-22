package com.exadel.demo.repository;

import com.exadel.demo.entity.RabbitMqMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RabbitMqMessageRepository extends MongoRepository<RabbitMqMessage, String> {

}
