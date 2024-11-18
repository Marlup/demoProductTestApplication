package com.marlo.demoProductTest.controller.controllerExceptions;

// Exception para 500
public class ProductInsertionException extends RuntimeException {
    public ProductInsertionException(String message) {
        super(message);
    }
}