package com.github.gelald.actuator.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Endpoint(id = "custom-endpoint")
public class CustomEndpoint {
    @ReadOperation
    public String customEndpoint() {
        return "Custom endpoint response";
    }
}
