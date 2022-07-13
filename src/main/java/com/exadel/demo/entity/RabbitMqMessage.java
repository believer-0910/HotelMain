package com.exadel.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RabbitMqMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String messageToRabbitMqStr;

    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageToRabbitMqStr() {
        return messageToRabbitMqStr;
    }

    public void setEmailDtoStr(String messageToRabbitMqStr) {
        this.messageToRabbitMqStr = messageToRabbitMqStr;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
