package com.example.genreprediction.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
@ConfigurationProperties(prefix = "app.ml")
public record MLProps(String baseUrl, int timeoutMs) {

}
