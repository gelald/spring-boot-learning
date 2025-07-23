package com.github.gelald.actuator.indicator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomIndicator implements HealthIndicator {

    @Override
    public Health health() {
        return Health.up().build();
    }
}
