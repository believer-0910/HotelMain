package com.exadel.demo.monitoring;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class DatabaseService implements HealthIndicator {
    private final String DATABASE_SERVICE = "DatabaseService";
    @Override
    public Health health() {
        if (isDatabaseHealthGood()){
            return Health.up().withDetail(DATABASE_SERVICE, ": service is running").build();
        }
        return Health.up().withDetail(DATABASE_SERVICE, ": service is not running").build();
    }

    private boolean isDatabaseHealthGood(){
        return true;
    }
}
