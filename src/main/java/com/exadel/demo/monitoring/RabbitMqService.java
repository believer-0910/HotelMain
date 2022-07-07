package com.exadel.demo.monitoring;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

public class RabbitMqService implements HealthIndicator {
    @Override
    public Health health() {
        String rabbitMQService = "RabbitMQService";
        if (isDatabaseHealthGood()){
            return Health.up().withDetail(rabbitMQService, ": service is running").build();
        }
        return Health.up().withDetail(rabbitMQService, ": service is not running").build();
    }

    private boolean isDatabaseHealthGood(){
        return true;
    }
}
