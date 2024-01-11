package com.restAPIMongo.service;

import com.restAPIMongo.dto.ProductDTO;
import com.restAPIMongo.exception.NotFoundException;
import com.restAPIMongo.mapper.ProductMapper;
import com.restAPIMongo.model.Product;
import com.restAPIMongo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    public ProductDTO save(ProductDTO productDTO) {
        Product product = productMapper.productDTOToProduct(productDTO);

        Product savedProduct = productRepository.save(product);

        ProductDTO savedProductDTO = productMapper.productToProductDTO(savedProduct);
        return savedProductDTO;
    }

    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> productList = productRepository.findAll(pageable);

        Page<ProductDTO> productDTOList = (Page<ProductDTO>) productList
                //.stream()
                .map(product -> productMapper.productToProductDTO(product));
                //.collect(Collectors.toList());
        return productDTOList;
    }

    public Optional<ProductDTO> findById(Long id) {

        Optional<Product> productOptional = Optional.ofNullable(productRepository.findById(id).orElseThrow(() -> new NotFoundException("No existe el ID: " + id)));

        Optional<ProductDTO> optionalProductDTO = productOptional
                    .map(product -> productMapper.productToProductDTO(product));

        return optionalProductDTO;
    }

    public Product updateById(Product product) {
        return productRepository.save(product);
    }

    public Map<Integer, String> deleteById(Long id) {
        Map<Integer, String> response = new HashMap<>();
        response.put(id.intValue() , "Valor Eliminado");
        productRepository.deleteById(id);
        return response;
    }

    public Optional<ProductDTO> findByName(String name) {
        Optional<ProductDTO> optionalProductDTO = productRepository.findByName(name);
        return optionalProductDTO;
    }

}
