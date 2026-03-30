package com.hwidong.ProductsMicroservice.products;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ErrorMessage extends Exception{
    private Date timestamp;
    private String message;
    private String details;

    public ErrorMessage(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
