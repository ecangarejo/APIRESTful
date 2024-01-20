package com.APIRESTful.persistence;

import com.APIRESTful.dto.ProductDTO;
import com.APIRESTful.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<ProductDTO> findByName(String name);

}
