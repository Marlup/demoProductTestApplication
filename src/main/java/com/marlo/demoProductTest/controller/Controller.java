package com.marlo.demoProductTest.controller;

import com.marlo.demoProductTest.repository.entity.ProductEntity;
import com.marlo.demoProductTest.service.IService;
import com.marlo.demoProductTest.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
public class Controller {

    private final ServiceImpl serviceImpl;

    @GetMapping("/products")
    public ResponseEntity<List<ProductEntity>> getAllProducts() {
        List<ProductEntity> product = serviceImpl.getAllProducts();

        try {
            return ResponseEntity.ok(product);
        }
        catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductEntity> getProductById(@PathVariable(required = false) Long id) {
        Optional<ProductEntity> product = serviceImpl.getProductById(id);

        return product.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

        /*
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.notFound().build();
        */
    }

    @PostMapping("/products")
    public ResponseEntity<List<ProductEntity>> addProduct(@RequestBody List<ProductEntity> productList) {
        List<ProductEntity> product = serviceImpl.save(productList);

        try {
            return ResponseEntity.ok(product);
        }
        catch (HttpClientErrorException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
