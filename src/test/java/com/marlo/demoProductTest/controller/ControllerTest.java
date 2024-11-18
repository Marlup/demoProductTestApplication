package com.marlo.demoProductTest.controller;

import com.marlo.demoProductTest.repository.entity.ProductEntity;
import com.marlo.demoProductTest.service.impl.ServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(Controller.class) // This does not load all the service layers of your application, so you need to
//explicitylu mock the service
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceImpl serviceImpl; // Mock the service

    //@Autowired
    //private ObjectMapper objectMapper; // For converting objects to JSON

    @Test
    @WithMockUser(username = "testuser", roles = "USER")  // Mock authenticated user
    void test_getAllProducts() throws Exception {
        List<ProductEntity> products = new ArrayList<>();
        // Product 1
        products.add(new ProductEntity(0, "comb", 3d, 2024));
        // Product 2
        products.add(new ProductEntity(1, "car", 100d, 2025));
        // Product 3
        products.add(new ProductEntity(2, "brush", 5d, 2023));

        when(serviceImpl.getAllProducts()).thenReturn(products);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(status().isOk()) // HTTP 200 status
                .andExpect(jsonPath("$.size()", is(3)))
                .andExpect(jsonPath("$[0].id", is(0)))
                .andExpect(jsonPath("$[0].name", is("comb")))
                .andExpect(jsonPath("$[0].price", is(3d)))
                .andExpect(jsonPath("$[0].yearProduct", is(2024)))

                .andExpect(jsonPath("$[1].name", is("car")))

                .andExpect(jsonPath("$[2].name", is("brush")));
    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")  // Mock authenticated user
    void test_getProductById() throws Exception {
        // Testing id
        final long testId = 0L;

        // Create one test product
        ProductEntity testingProduct = new ProductEntity(0L, "notebook", 5d, 2022);

        // Mock the call of the service and the return.
        when(serviceImpl.getProductById(testId)).thenReturn(Optional.of(testingProduct));

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}", testId))
                .andExpect(jsonPath("$.id", is(0)))
                .andExpect(jsonPath("$.name", is("notebook")))
                .andExpect(jsonPath("$.price", is(5d)))
                .andExpect(jsonPath("$.yearProduct", is(2022)))
                .andReturn();
        System.out.println("Response mvc: " + result.getResponse().getContentAsString());
        System.out.println("End-Response mvc");
    }

    /*
    @Test
    @WithMockUser(username = "testuser", roles = "USER", password="password")
    @AutoConfigureMockMvc(addFilters = false)
    void test_addProduct() throws Exception {
        // Arrange: Create input and expected output
        List<ProductEntity> inputProducts = List.of(
                new ProductEntity(0, "comb", 3d, 2024),
                new ProductEntity(1, "car", 100d, 2025)
        );

        List<ProductEntity> savedProducts = List.of(
                new ProductEntity(0, "comb", 3d, 2024),
                new ProductEntity(1, "car", 100d, 2025)
        );

        when(service.save(inputProducts)).thenReturn(savedProducts);

        // Act & Assert: Perform POST request and validate response
        this.mockMvc.perform(MockMvcRequestBuilders.post("/products"))
                .andExpect(status().isOk()) // Verify HTTP status
                .andExpect(jsonPath("$.size()", is(2))) // Validate list size
                .andExpect(jsonPath("$[0].name", is("comb"))) // Validate first product
                .andExpect(jsonPath("$[1].name", is("car"))); // Validate second product
                        //.contentType("application/json") // Set content type as JSON
                        //.content(objectMapper.writeValueAsString(inputProducts))) // Send JSON body
    }
     */
}
