package com.example.genreprediction.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableConfigurationProperties(MLProps.class)
public class HttpClientConfig {

    @Bean
    WebClient mlClient(MLProps props){
        return WebClient.builder()
                .baseUrl(props.baseUrl())
                .build();
    }
}
