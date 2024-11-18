package com.marlo.demoProductTest.service;

import com.marlo.demoProductTest.repository.ProductRepository;
import com.marlo.demoProductTest.repository.entity.ProductEntity;
import com.marlo.demoProductTest.service.impl.ServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ServiceImpl serviceImpl;

    @Mock
    private ProductRepository repository;

    @Test
    void test_getAllProducts() {
        List<ProductEntity> products = new ArrayList<>();
        // Product 1
        products.add(new ProductEntity(0, "comb", 3d, 2024));
        // Product 2
        products.add(new ProductEntity(1, "car", 100d, 2025));
        // Product 3
        products.add(new ProductEntity(2, "brush", 5d, 2023));

        when(serviceImpl.getAllProducts()).thenReturn(products);

        List<ProductEntity> result = serviceImpl.getAllProducts();

        assertEquals(result.get(0), new ProductEntity(0, "comb", 3d, 2024));
        assertEquals(result.get(1), new ProductEntity(1, "car", 100d, 2025));
        assertEquals(result.get(2), new ProductEntity(2, "brush", 5d, 2023));
    }

    @Test
    void getProductById() {

        final long testId = 0L;
        // Create one test product
        Optional<ProductEntity> testingProduct = Optional.of(new ProductEntity(testId, "comb", 3d, 2024));

        // Mokes the service to get the product by ID and returns an Optional
        when(serviceImpl.getProductById(testId)).thenReturn(testingProduct);

        //Optional<ProductEntity> result = service.getProductById(testId);
        Optional<ProductEntity> result = serviceImpl.getProductById(testId);

        assertEquals(result, testingProduct);
    }

    @Test
    void test_save() {
        List<ProductEntity> products = new ArrayList<>();
        // Product 1
        products.add(new ProductEntity(0, "comb", 3d, 2024));
        // Product 2
        products.add(new ProductEntity(1, "car", 100d, 2025));
        // Product 3
        products.add(new ProductEntity(2, "brush", 5d, 2023));

        when(serviceImpl.save(products)).thenReturn(products);

        //Optional<List<ProductEntity>> result = service.save(products);
        List<ProductEntity> result = serviceImpl.save(products);

        assertEquals(products, result);
        assertThat(repository).isNotNull();
    }
}
