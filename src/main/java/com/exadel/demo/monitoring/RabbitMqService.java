package com.exadel.demo.monitoring;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

public class RabbitMqService implements HealthIndicator {
    private final String RABBITMQ_SERVICE = "RabbitMQService";
    @Override
    public Health health() {
        if (isDatabaseHealthGood()){
            return Health.up().withDetail(RABBITMQ_SERVICE, ": service is running").build();
        }
        return Health.up().withDetail(RABBITMQ_SERVICE, ": service is not running").build();
    }

    private boolean isDatabaseHealthGood(){
        return true;
    }
}
