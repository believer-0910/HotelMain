package com.exadel.demo.service.rabbit_mq;

import com.exadel.demo.entity.RabbitMqMessage;
import com.exadel.demo.repository.RabbitMqMessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RabbitMqMessageService {

    private final RabbitMqMessageRepository rabbitMqMessageRepository;

    public RabbitMqMessageService(RabbitMqMessageRepository rabbitMqMessageRepository) {
        this.rabbitMqMessageRepository = rabbitMqMessageRepository;
    }

    public void addRabbitMqMessageToDatabase(RabbitMqMessage rabbitMqMessage){
        rabbitMqMessageRepository.save(rabbitMqMessage);
    }

    public List<RabbitMqMessage> getAllInProgressMessages(){
        return rabbitMqMessageRepository.findAllInProgressMessages();
    }
}
