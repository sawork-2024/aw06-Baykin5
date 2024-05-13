package com.example.webpos.mapper;

import java.util.Collection;
import org.mapstruct.Mapper;

import com.example.webpos.model.Product;
import com.example.webpos.rest.dto.ProductDto;

@Mapper
public interface ProductMapper {
  ProductDto toProductDto(Product Product);

  Collection<ProductDto> toProductsDto(Collection<Product> pets);

  Collection<Product> toProducts(Collection<ProductDto> pets);

  Product toProduct(ProductDto ProductDto);


}
