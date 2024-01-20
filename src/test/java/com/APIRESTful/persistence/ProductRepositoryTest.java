package com.APIRESTful.persistence;

import com.APIRESTful.dto.ProductDTO;
import com.APIRESTful.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        Product product = Product.builder()
                .name("Ball")
                .build();
        testEntityManager.persist(product);
    }

    @Test
    void findByNameFound() {
        Optional<ProductDTO> product = productRepository.findByName("Ball");
        assertEquals(product.get().getName(), "Ball");
        System.out.println("product.get() = " + product.get().getName());
    }

    @Test
    void findByNameNotFound() {
        Optional<ProductDTO> product = productRepository.findByName("Soccer");
        assertEquals(product, Optional.empty());
        System.out.println("product.get() = " + product.isEmpty());
    }
}