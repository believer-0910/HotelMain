package com.exadel.demo.service.rabbitMq;

import com.exadel.demo.dto.EmailDto;
import com.exadel.demo.dto.rabbitMqMessage.MessageToRabbitMQ;
import com.exadel.demo.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static com.exadel.demo.service.config.MQConfig.EXCHANGE;
import static com.exadel.demo.service.config.MQConfig.ROUTING_KEY;

@Service
public class PublishingMessage {

    private static final Logger log = LogManager.getLogger(PublishingMessage.class);
    private final String bookedRoom = "ROOM BOOKED";
    private final String bookedRoomMessage = "You have just booked a room";
    private final String newBook = "NEW ROOM";
    private final String newRoomMessage = "You can book a new room";

    private final RabbitTemplate rabbitTemplate;
    private final UserService userService;

    public PublishingMessage(RabbitTemplate rabbitTemplate, UserService userService) {
        this.rabbitTemplate = rabbitTemplate;
        this.userService = userService;
    }

    public void sendMessageToQueueToSendEmailToUser(String email) {
        Long userId = userService.getUserIdByEmail(email).getId();
        EmailDto emailDto = new EmailDto(userId, bookedRoom, bookedRoomMessage);
        publishMessage(new MessageToRabbitMQ(true, emailDto));
    }

    public void sendMessageToQueueToSendEmailToAllUsers() {
        EmailDto emailDto = new EmailDto(newBook, newRoomMessage);
        publishMessage(new MessageToRabbitMQ(false, emailDto));
    }

    private void publishMessage(MessageToRabbitMQ messageToRabbitMQ) {
        log.info("Message is published");
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, messageToRabbitMQ);
    }

}
