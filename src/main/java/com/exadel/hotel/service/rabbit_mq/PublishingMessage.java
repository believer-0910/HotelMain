package com.exadel.hotel.service.rabbit_mq;

import com.exadel.hotel.dto.EmailDto;
import com.exadel.hotel.dto.rabbit_mq_message.MessageToRabbitMQ;
import com.exadel.hotel.entity.RabbitMqMessage;
import com.exadel.hotel.entity.enums.Status;
import com.exadel.hotel.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static com.exadel.hotel.service.config.MQConfig.EXCHANGE;
import static com.exadel.hotel.service.config.MQConfig.ROUTING_KEY;

@Service
public class PublishingMessage {

    private static final Logger log = LogManager.getLogger(PublishingMessage.class);
    private final RabbitTemplate rabbitTemplate;
    private final UserService userService;
    private final ObjectMapper objectMapper;
    private final RabbitMqMessageService rabbitMqMessageService;

    public PublishingMessage(RabbitTemplate rabbitTemplate, UserService userService, ObjectMapper objectMapper, RabbitMqMessageService rabbitMqMessageService) {
        this.rabbitTemplate = rabbitTemplate;
        this.userService = userService;
        this.objectMapper = objectMapper;
        this.rabbitMqMessageService = rabbitMqMessageService;
    }

    public void sendMessageToQueueToSendEmailToUser(String email) {
        Long userId = userService.getUserIdByEmail(email).getId();
        String bookedRoom = "ROOM BOOKED";
        String bookedRoomMessage = "You have just booked a room";
        EmailDto emailDto = new EmailDto(userId, bookedRoom, bookedRoomMessage);
        sendMessageToRabbitMq(emailDto);
    }

    public void sendMessageToQueueToSendEmailToAllUsers() {
        String newBook = "NEW ROOM";
        String newRoomMessage = "You can book a new room";
        EmailDto emailDto = new EmailDto(newBook, newRoomMessage);
        sendMessageToRabbitMq(emailDto);
    }

    public void publishMessage(MessageToRabbitMQ messageToRabbitMQ) {
        log.info("Message is published");
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, messageToRabbitMQ);
    }

    public void sendMessageToRabbitMq(EmailDto emailDto){
        MessageToRabbitMQ messageToRabbitMQ = new MessageToRabbitMQ(true, emailDto);
        RabbitMqMessage rabbitMqMessage = new RabbitMqMessage();
        try {
            rabbitMqMessage.setMessageToRabbitMqStr(objectMapper.writeValueAsString(messageToRabbitMQ));
            publishMessage(messageToRabbitMQ);
        } catch (Exception e) {
            rabbitMqMessage.setStatus(Status.IN_PROCESS);
            rabbitMqMessageService.addRabbitMqMessageToDatabase(rabbitMqMessage);
        }
        rabbitMqMessage.setStatus(Status.SUCCESS);
        rabbitMqMessageService.addRabbitMqMessageToDatabase(rabbitMqMessage);
    }
}
