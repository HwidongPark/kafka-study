package com.hwidong.ProductsMicroservice;

import com.hwidong.ProductsMicroservice.products.ProductCreatedEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.producer.key-serializer}")
    private String keySerializer;

    @Value("${spring.kafka.producer.value-serializer}")
    private String valueSerializer;

    @Value("${spring.kafka.producer.acks}")
    private String acks;

    @Value("${spring.kafka.producer.properties.delivery.timeout.ms}")
    private String deliveryTimeout;

    @Value("${spring.kafka.producer.properties.linger.ms}")
    private String linger;

    @Value("${spring.kafka.producer.properties.request.timeout.ms}")
    private String requestTimeout;



    Map<String, Object> producerConfigs() {
        Map<String, Object> config = new HashMap<>();

        // bootstarp-server설정
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        // key serializer설정
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);

        // value serializer 설정
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);

        // acks 설정
        config.put(ProducerConfig.ACKS_CONFIG, acks);

        // delivery timeout 설정
        config.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, deliveryTimeout);

        // linger 설정
        config.put(ProducerConfig.LINGER_MS_CONFIG, linger);

        // request timetout ms 설정
        config.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, requestTimeout);



        return config;
    }


    /**
     * kafka producer의 configuration 설정
     * @return
     */
    @Bean
    ProducerFactory<String, ProductCreatedEvent> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    /**
     * config
     * @return
     */
    @Bean
    KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate() {
        return new KafkaTemplate<String, ProductCreatedEvent>(producerFactory());
    }

    @Bean
    NewTopic createTopic() {
        return TopicBuilder
                // topic 이름
                .name("product-created-events-topic")
                // partition 개수 설정
                .partitions(3)
                // replica개수 설정
                .replicas(3)
                // isr설정
                .configs(Map.of("min.insync.replicas", "2"))
                .build();
    }
}