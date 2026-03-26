package com.hwidong.ProductsMicroservice;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    NewTopic createTopic() {
        return TopicBuilder.name("").build();
    }
}