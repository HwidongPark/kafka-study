package com.hwidong.ProductsMicroservice.rest;

import com.hwidong.ProductsMicroservice.products.ErrorMessage;
import com.hwidong.ProductsMicroservice.products.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.ExecutionException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {


    private final ProductService productService;

    @PostMapping("")
    public ResponseEntity<Object> createProduct(@RequestBody CreateProductRestModel product) {

        String productId = null;
        try {
            productId = productService.createProduct(product);
        } catch (Exception e) {
            this.log.error(e.getMessage(), e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorMessage(new Date(), e.getMessage(), "/products"));
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productId);
    }

}
