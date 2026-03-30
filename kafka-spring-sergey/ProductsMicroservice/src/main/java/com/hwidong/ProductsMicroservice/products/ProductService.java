package com.hwidong.ProductsMicroservice.products;

import com.hwidong.ProductsMicroservice.rest.CreateProductRestModel;

import java.util.concurrent.ExecutionException;

public interface ProductService {
    String createProduct(CreateProductRestModel productRestModel) throws ExecutionException, InterruptedException;
}
