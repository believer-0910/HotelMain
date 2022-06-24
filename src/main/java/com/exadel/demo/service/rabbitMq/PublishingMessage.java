package com.exadel.demo.service.rabbitMq;

import com.exadel.demo.dto.EmailDto;
import com.exadel.demo.dto.rabbitMqMessage.MessageToRabbitMQ;
import com.exadel.demo.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.exadel.demo.service.config.MQConfig.EXCHANGE;
import static com.exadel.demo.service.config.MQConfig.ROUTING_KEY;

@Service
public class PublishingMessage {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private UserService userService;

    private static final Logger log = LogManager.getLogger(PublishingMessage.class);

    public void sendMessageToQueueToSendEmailToUser(String email){
        Long userId = userService.getUserIdByEmail(email);
        EmailDto emailDto = new EmailDto(userId, "ROOM BOOKED", "You have just booked a room");
        publishMessage(new MessageToRabbitMQ(true, emailDto));
    }

    public void sendMessageToQueueToSendEmailToAllUsers(){
        EmailDto emailDto = new EmailDto("NEW ROOM", "You can book a new room");
        publishMessage(new MessageToRabbitMQ(false, emailDto));
    }

    private void publishMessage(MessageToRabbitMQ messageToRabbitMQ){
        log.info("Message is published");
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, messageToRabbitMQ);
    }

}
