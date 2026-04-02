package com.hwidong.core;


import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProductCreatedEvent {
    private String productId;
    private String title;
    private BigDecimal price;
    private Integer quantity;

}
