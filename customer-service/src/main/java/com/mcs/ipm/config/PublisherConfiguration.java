package com.mcs.ipm.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PublisherConfiguration {

    @Value("${customer.rabbitmq.exchange}")
    private String customerExchange;

    @Bean
    public TopicExchange customerTopic() {
        return new TopicExchange(customerExchange);
    }
}
