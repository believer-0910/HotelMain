package com.exadel.demo.monitoring;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class DatabaseService implements HealthIndicator {
    @Override
    public Health health() {
        String databaseService = "DatabaseService";
        if (isDatabaseHealthGood()){
            return Health.up().withDetail(databaseService, ": service is running").build();
        }
        return Health.up().withDetail(databaseService, ": service is not running").build();
    }

    private boolean isDatabaseHealthGood(){
        return true;
    }
}
