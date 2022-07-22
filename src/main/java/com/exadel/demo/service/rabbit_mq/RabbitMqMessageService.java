package com.exadel.demo.service.rabbit_mq;

import com.exadel.demo.entity.RabbitMqMessage;
import com.exadel.demo.entity.enums.Status;
import com.exadel.demo.repository.RabbitMqMessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RabbitMqMessageService {

    private final RabbitMqMessageRepository rabbitMqMessageRepository;

    public RabbitMqMessageService(RabbitMqMessageRepository rabbitMqMessageRepository) {
        this.rabbitMqMessageRepository = rabbitMqMessageRepository;
    }

    public void addRabbitMqMessageToDatabase(RabbitMqMessage rabbitMqMessage){
        rabbitMqMessageRepository.save(rabbitMqMessage);
    }

    public List<RabbitMqMessage> getByStatus(Status status){
        return rabbitMqMessageRepository.findAll().stream().filter(rabbitMqMessage -> rabbitMqMessage.getStatus().equals(status)).collect(Collectors.toList());
    }
}
