package com.APIRESTful.service;

import com.APIRESTful.dto.ProductDTO;
import com.APIRESTful.entities.Department;
import com.APIRESTful.exception.NotFoundException;
import com.APIRESTful.mapper.DepartmentMapper;
import com.APIRESTful.mapper.ProductMapper;
import com.APIRESTful.entities.Product;
import com.APIRESTful.persistence.DepartmentRepository;
import com.APIRESTful.persistence.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    private ProductMapper productMapper;

    public ProductDTO save(ProductDTO productDTO) {
        Product product = productMapper.productDTOToProduct(productDTO);

        Product savedProduct = productRepository.save(product);

        ProductDTO savedProductDTO = productMapper.productToProductDTO(savedProduct);
        return savedProductDTO;
    }

    /*public Product save(ProductDTO productDTO) {
        Product product = productMapper.productDTOToProduct(productDTO);

        Product savedProduct = productRepository.save(product);

        //ProductDTO savedProductDTO = productMapper.productToProductDTO(savedProduct);
        return savedProduct;
    }*/

    public Product saveProduct(ProductDTO productDTO) {
        Product product = Product.builder()
                .name(productDTO.getName())
                .creationDate(productDTO.getCreationDate())
                .department(Department.builder()
                        .name(productDTO.getDepartmentDTO().getName())
                        .build())
                .build();
        return productRepository.save(product);
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
        //Optional<Product> productOptional = Optional.ofNullable(productRepository.findById(id).orElseThrow(NotFoundException::new));

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
