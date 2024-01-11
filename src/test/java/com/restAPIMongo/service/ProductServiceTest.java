package com.restAPIMongo.service;

import com.restAPIMongo.dto.ProductDTO;
import com.restAPIMongo.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        ProductDTO product = ProductDTO.builder()
                .id(1L)
                .name("Box")
                .build();
        Mockito.when(productRepository.findByName("Box")).thenReturn(Optional.of(product));
    }

    @Test
    @DisplayName("Prueba para obtener el producto con un nombre válido")
    void findByNameFound() {
        String productName = "Box";
        Optional<ProductDTO> product = productService.findByName(productName);
        assertEquals(productName, product.get().getName());
        System.out.println("ProductId = " + product.get().getId());
        System.out.println("ProductName = " + product.get().getName());
    }
}