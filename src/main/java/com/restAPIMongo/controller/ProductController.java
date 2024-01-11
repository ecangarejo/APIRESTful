package com.restAPIMongo.controller;

import com.restAPIMongo.dto.ProductDTO;
import com.restAPIMongo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/mongo")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDTO> save(@RequestBody ProductDTO product) {
        productService.save(product);
        return ResponseEntity.ok(product);
    }

    /*
    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        List<Product> productList = productService.findAll();
        return ResponseEntity.ok(productList);
    }
    */

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable) {
        //Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "name"));
        Page<ProductDTO> productList = productService.findAll(pageable);
        return ResponseEntity.ok(productList);

        /*List<ProductDTO> productDTOList = productService.findAll()
                .stream()
                .map(productList -> ProductDTO.builder()
                        .id(productList.getId())
                        .name(productList.getName())
                        .creationDate(String.valueOf(productList.getCreationDate()))
                        .build())
                .toList();
        return ResponseEntity.ok(productDTOList);*/
    }

    /*
    @GetMapping("{id}")
    public ResponseEntity<Optional<Product>> findById(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        return ResponseEntity.ok(productOptional);
    }
    */

    @GetMapping("{id}")
    public ResponseEntity<Optional<ProductDTO>> findById(@PathVariable Long id) {
        Optional<ProductDTO> productOptional = productService.findById(id);
        return ResponseEntity.ok(productOptional);

        /*ProductDTO optionalProductDTO = ProductDTO.builder()
                .id(productOptional.get().getId())
                .name(productOptional.get().getName())
                .build();

        return ResponseEntity.ok(Optional.ofNullable(optionalProductDTO));*/
    }

    @PutMapping
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO product) {
        Optional<ProductDTO> productOptional = productService.findById(product.getId());
        if (productOptional.isPresent())
            productService.save(product);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("{id}")
    public Map<Integer, String> deleteById(@PathVariable Long id) {
        Map<Integer, String> response = productService.deleteById(id);
        return ResponseEntity.ok(response).getBody();
    }

    @GetMapping("/name")
    public ResponseEntity<Optional<ProductDTO>> findByName(@RequestParam String name) {
        Optional<ProductDTO> optionalProductDTO = productService.findByName(name);
        return ResponseEntity.ok(optionalProductDTO);
    }

}
