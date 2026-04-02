package com.hwidong.EmailnotificationMicroservice.handler;

import com.hwidong.core.ProductCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @KafkaListener는 Class, Method위에 붙을 수 있는 annotation이다.
 *
 * Kafka topic으로부터 새로운 message를 받을 때 invoked되도록 하는 annotation이다.
 */
@KafkaListener(topics = "product-created-events-topic")
@Slf4j
@Component
public class ProductCreatedEventHandler {


    @KafkaHandler
    public void handle(ProductCreatedEvent productCreatedEvent) {
        log.info("Received a new event: " + productCreatedEvent.getTitle());
    }

}
