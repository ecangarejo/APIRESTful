package com.APIRESTful.mapper;

import com.APIRESTful.dto.ProductDTO;
import com.APIRESTful.entities.Product;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    /*@Mappings({
            @Mapping(source = "creationDate", target = "creationDate", dateFormat = "yyyy-MM-dd")
    })*/
    ProductDTO productToProductDTO(Product product);

    @InheritInverseConfiguration
    Product productDTOToProduct(ProductDTO productDTO);

}
