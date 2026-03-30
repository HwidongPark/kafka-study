package com.hwidong.ProductsMicroservice.products;

import com.hwidong.ProductsMicroservice.rest.CreateProductRestModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    // kafka producer의 Wrapper Class라고 생각하면 됨
    private final KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;

    @Override
    public String createProduct(CreateProductRestModel productRestModel) throws ExecutionException, InterruptedException {
        String productId = UUID.randomUUID().toString();

        // TODO: Product details 저장한 후 Event publish
        ProductCreatedEvent productCreatedEvent = ProductCreatedEvent
                .builder()
                .productId(productId)
                .title(productRestModel.getTitle())
                .price(productRestModel.getPrice())
                .quantity(productRestModel.getQuantity())
                .build();

        // 1. asynchronously하게 message를 publish하는 방법
//        CompletableFuture<SendResult<String, ProductCreatedEvent>> future =
//                kafkaTemplate.send("product-created-events-topic", productId, productCreatedEvent);
//
//        future.whenComplete((result, exception) -> {
//            if (exception != null) {
//                this.log.error("**** Failed to send message: " + exception.getMessage());
//            } else {
//                this.log.info("**** Message sent successfully: " + result.getRecordMetadata());
//            }
//        });

        // future를 block함. 즉, future의 동작이 끝날때까지 기다림(-> synchronous 하게 만듦)
        // future.join();


        // 2. synchronously하게 message를 publish하는 방법
        this.log.info("Before publishing a ProductCreatedEvent");

        SendResult<String, ProductCreatedEvent> result = kafkaTemplate.send("product-created-events-topic", productId, productCreatedEvent).get();

        this.log.info("Partition: " + result.getRecordMetadata().partition());
        this.log.info("Topic: " + result.getRecordMetadata().topic());
        this.log.info("Offset: " + result.getRecordMetadata().offset());
        this.log.info("**** Message sent successfully: " + result.getRecordMetadata());

        return productId;
    }
}
