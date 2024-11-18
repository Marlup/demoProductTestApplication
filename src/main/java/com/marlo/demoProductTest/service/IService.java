package com.marlo.demoProductTest.service;

import com.marlo.demoProductTest.repository.entity.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface IService {
    List<ProductEntity> getAllProducts();
    List<ProductEntity> save(List<ProductEntity> products);
    Optional<ProductEntity> getProductById(long id);
}
