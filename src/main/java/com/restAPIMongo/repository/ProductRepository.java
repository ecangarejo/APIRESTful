package com.restAPIMongo.repository;

import com.restAPIMongo.dto.ProductDTO;
import com.restAPIMongo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<ProductDTO> findByName(String name);

}
