package com.exadel.demo.service.rabbit_mq;

import com.exadel.demo.dto.rabbit_mq_message.MessageToRabbitMQ;
import com.exadel.demo.entity.RabbitMqMessage;
import com.exadel.demo.entity.enums.Status;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableScheduling
public class ResendInProcessMessageService {

    private final RabbitMqMessageService rabbitMqMessageService;
    private final ObjectMapper objectMapper;
    private final PublishingMessage publishingMessage;

    public ResendInProcessMessageService(RabbitMqMessageService rabbitMqMessageService, ObjectMapper objectMapper, PublishingMessage publishingMessage) {
        this.rabbitMqMessageService = rabbitMqMessageService;
        this.objectMapper = objectMapper;
        this.publishingMessage = publishingMessage;
    }

    @Scheduled(fixedRate = 60*60*1000)
    public void resendMessagesToRabbitMq() {
        List<RabbitMqMessage> rabbitMqMessageList = rabbitMqMessageService.getByStatus(String.valueOf(Status.IN_PROCESS));
        for (RabbitMqMessage rabbitMqMessage: rabbitMqMessageList){
            try {
                MessageToRabbitMQ messageToRabbitMQ = objectMapper.readValue(rabbitMqMessage.getMessageToRabbitMqStr(), MessageToRabbitMQ.class);
                publishingMessage.publishMessage(messageToRabbitMQ);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
