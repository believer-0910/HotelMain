package com.exadel.demo.entity;

import com.exadel.demo.entity.enums.Status;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document("rabbit_mq_message")
public class RabbitMqMessage {
    @Id
    private String id;

    private String messageToRabbitMqStr;

    private Status status;

    public RabbitMqMessage() {
    }

    public RabbitMqMessage(String id, String messageToRabbitMqStr, Status status) {
        this.id = id;
        this.messageToRabbitMqStr = messageToRabbitMqStr;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessageToRabbitMqStr() {
        return messageToRabbitMqStr;
    }

    public void setMessageToRabbitMqStr(String messageToRabbitMqStr) {
        this.messageToRabbitMqStr = messageToRabbitMqStr;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
