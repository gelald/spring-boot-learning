package com.github.gelald.actuator.config;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Configuration
public class CustomActuatorConfig {

    // 自定义端点
    // 效果：可以通过 http://<ip>:<port>/actuator/<endpoint-id> 来访问
    @Component
    @Endpoint(id = "features")
    public class FeaturesEndpoint {

        private final Map<String, Feature> features = new ConcurrentHashMap<>();

        public FeaturesEndpoint() {
            features.put("darkMode", new Feature("darkMode", true));
            features.put("betaFeatures", new Feature("betaFeatures", false));
        }

        // 对应 Get 请求方法
        @ReadOperation
        public Map<String, Feature> features() {
            return features;
        }

        // 对应 Post 请求方法，@Selector 对应路径参数，/actuator/features/{name}
        @ReadOperation
        public Feature feature(@Selector String name) {
            return features.get(name);
        }

        // 对应 Post 请求方法，@Selector 对应路径参数，/actuator/features/{name}
        @WriteOperation
        public Feature updateFeature(@Selector String name, boolean enabled) {
            Feature feature = features.get(name);
            if (feature != null) {
                feature.setEnabled(enabled);
            }
            return feature;
        }

        @Data
        @AllArgsConstructor
        public static class Feature {
            private String name;
            private boolean enabled;
        }
    }

    // 自定义健康检查
    // 效果：访问 http://<ip>:<port>/actuator/health 时，会展示自定义的健康检查
    // 访问 http://<ip>:<port>/actuator/metrics/<health-check-name> 时，可以查看自定义健康检查的明细
    @Component("customCheck")
    public class CustomIndicator implements HealthIndicator {
        @Override
        public Health health() {
            return Health.up().build();
        }
    }

    // 自定义指标
    // 效果：访问 http://<ip>:<port>/actuator/metrics 时，会展示自定义的指标
    // 访问 http://<ip>:<port>/actuator/metrics/<metrics-name> 时，可以查看自定义指标的明细
    @Component
    public class CustomMetrics {
        private static final SecureRandom RANDOM = new SecureRandom();
        private final Counter counter;
        private final DistributionSummary distributionSummary;

        public CustomMetrics(MeterRegistry meterRegistry) {
            this.counter = Counter.builder("custom.count")
                    .description("Total number of reading")
                    .register(meterRegistry);

            this.distributionSummary = DistributionSummary.builder("custom.amount")
                    .description("Reading cmout distribution")
                    .baseUnit("USD")
                    .register(meterRegistry);

            int times = RANDOM.nextInt(1, 10);
            log.info("record {} times after init", times);
            for (int i = 0; i < times; i++) {
                this.recordReading();
            }
        }

        public void recordReading() {
            counter.increment();
            distributionSummary.record(RANDOM.nextDouble(20.0, 99.9));
        }
    }
}
