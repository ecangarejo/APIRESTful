package com.restAPIMongo.controller;

import com.restAPIMongo.dto.ProductDTO;
import com.restAPIMongo.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private ProductDTO product;

    @BeforeEach
    void setUp() {
        product = ProductDTO.builder()
                .id(1L)
                .name("Box")
                .build();
    }

    @Test
    void saveProduct() throws Exception {
        ProductDTO postProduct = ProductDTO.builder()
                .name("Box")
                .build();
        Mockito.when(productService.save(postProduct)).thenReturn(product);
        mockMvc.perform(post("/mongo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\"name\":\"Box\"\n" +
                                //"\"name\":\"Box\,"\n" +
                                "}"))
                        .andExpect(status().isOk());
    }

    @Test
    void findProductById() throws Exception {
        Mockito.when(productService.findById(1L)).thenReturn(Optional.ofNullable(product));
        mockMvc.perform(get("/mongo/1")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.name").value(product.getName()));
        System.out.println(product.getName());
    }
}