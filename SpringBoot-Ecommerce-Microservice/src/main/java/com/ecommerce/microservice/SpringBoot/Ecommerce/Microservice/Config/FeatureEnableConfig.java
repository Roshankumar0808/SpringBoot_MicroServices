package com.ecommerce.microservice.SpringBoot.Ecommerce.Microservice.Config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
@Data
public class FeatureEnableConfig {

    @Value("${features.user-tracking-enabled}")
    private boolean isUserTrackingEnabled;
}
