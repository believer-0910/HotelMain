package com.exadel.demo.entity;

import com.exadel.demo.entity.enums.Status;

import javax.persistence.*;

@Entity
public class RabbitMqMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String messageToRabbitMqStr;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
