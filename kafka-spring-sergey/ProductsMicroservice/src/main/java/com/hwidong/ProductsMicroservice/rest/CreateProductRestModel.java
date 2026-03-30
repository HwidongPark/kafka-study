package com.hwidong.ProductsMicroservice.rest;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class CreateProductRestModel {

    private String title;
    private BigDecimal Price;
    private Integer quantity;

}
