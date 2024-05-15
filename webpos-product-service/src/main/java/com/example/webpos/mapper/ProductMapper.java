package com.example.webpos.mapper;

import java.util.Collection;
import org.mapstruct.Mapper;

import com.example.webpos.model.Product;
import org.mapstruct.factory.Mappers;
import org.openapitools.model.ProductDto;


@Mapper
public interface ProductMapper {

  ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
  ProductDto toProductDto(Product Product);

  Collection<ProductDto> toProductsDto(Collection<Product> pets);

  Collection<Product> toProducts(Collection<ProductDto> pets);

  Product toProduct(ProductDto ProductDto);


}
