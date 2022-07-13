package com.exadel.demo.service.rabbit_mq;

import com.exadel.demo.dto.rabbit_mq_message.MessageToRabbitMQ;
import com.exadel.demo.entity.RabbitMqMessage;
import com.exadel.demo.repository.RabbitMqMessageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class RabbitMqMessageService {

    private final ObjectMapper objectMapper;
    private final RabbitMqMessageRepository rabbitMqMessageRepository;

    public RabbitMqMessageService(ObjectMapper objectMapper, RabbitMqMessageRepository rabbitMqMessageRepository) {
        this.objectMapper = objectMapper;
        this.rabbitMqMessageRepository = rabbitMqMessageRepository;
    }

    public boolean save(MessageToRabbitMQ messageToRabbitMQ){
        RabbitMqMessage rabbitMqMessage = new RabbitMqMessage();
        try {
            rabbitMqMessage.setEmailDtoStr(objectMapper.writeValueAsString(messageToRabbitMQ));
        } catch (IOException e){
            return false;
        }
        rabbitMqMessage.setStatus("in process");
        rabbitMqMessageRepository.save(rabbitMqMessage);
        return true;
    }

    public List<RabbitMqMessage> getAll(){
        return rabbitMqMessageRepository.findAll();
    }
}
