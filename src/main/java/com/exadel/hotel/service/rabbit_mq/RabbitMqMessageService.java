package com.exadel.hotel.service.rabbit_mq;

import com.exadel.hotel.entity.RabbitMqMessage;
import com.exadel.hotel.repository.RabbitMqMessageRepository;
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

    public List<RabbitMqMessage> getByStatus(String status){
        return rabbitMqMessageRepository.findByStatus(status);
    }
}
