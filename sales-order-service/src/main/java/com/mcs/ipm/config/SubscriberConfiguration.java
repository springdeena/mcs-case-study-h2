package com.mcs.ipm.config;

import com.mcs.ipm.mq.CustomerSubscriber;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SubscriberConfiguration {

    @Value("${customer.rabbitmq.routingkey}")
    private String routingKey;

    @Value("${customer.rabbitmq.exchange}")
    private String customerExchange;

    @Bean
    public TopicExchange customerTopic() {
        return new TopicExchange(customerExchange);
    }

    @Bean
    public CustomerSubscriber subscriber() {
        return new CustomerSubscriber();
    }

    @Bean
    public Queue customerQueue() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding binding(TopicExchange topic, Queue customerQueue) {
        return BindingBuilder.bind(customerQueue).to(topic).with(routingKey);
    }
}
