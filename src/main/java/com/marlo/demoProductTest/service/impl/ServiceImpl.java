package com.marlo.demoProductTest.service.impl;

import com.marlo.demoProductTest.repository.ProductRepository;
import com.marlo.demoProductTest.repository.entity.ProductEntity;
import com.marlo.demoProductTest.service.IService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ServiceImpl implements IService {

    private final ProductRepository productRepository;

    @Override
    public List getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductEntity> save(List<ProductEntity> products) {
        return productRepository.saveAll(products);
    }

    @Override
    public Optional<ProductEntity> getProductById(long id) {
        return productRepository.findById(id);
    }
}
