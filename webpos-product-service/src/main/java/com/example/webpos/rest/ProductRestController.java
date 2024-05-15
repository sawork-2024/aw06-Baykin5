package com.example.webpos.rest;

import java.util.ArrayList;
import java.util.List;

import com.example.webpos.api.ProductsApi;
import org.openapitools.model.ProductDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.webpos.biz.ProductService;
import com.example.webpos.mapper.ProductMapper;
import com.example.webpos.model.Product;


@RestController
public class ProductRestController implements ProductsApi {
  @Autowired
  private ProductService productService;


  @Override
  public ResponseEntity<ProductDto> getProduct(Integer productId) {
    ProductDto product = ProductMapper.INSTANCE.toProductDto(this.productService.findProductById(productId));
    if (product == null) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(product, HttpStatus.OK);
  }


  @Override
  public ResponseEntity<List<ProductDto>> listProducts(){
    List<ProductDto> products = new ArrayList<>(ProductMapper.INSTANCE.toProductsDto(this.productService.products()));
    if (products.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(products,HttpStatus.OK);
  }

  @Override
  public ResponseEntity<ProductDto> updateProduct(Integer productId,ProductDto productDto){
    Product currentProduct = this.productService.findProductById(productId);
    if (currentProduct == null){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    currentProduct.name = productDto.getName();
    currentProduct.price = productDto.getPrice();
    currentProduct.image = productDto.getImage();
    this.productService.addProduct(currentProduct);
    return new ResponseEntity<>(ProductMapper.INSTANCE.toProductDto(currentProduct),HttpStatus.NO_CONTENT);
  }

  @Override
  public ResponseEntity<ProductDto> deleteProduct(Integer productId){
    Product product = this.productService.findProductById(productId);
    if (product == null){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    this.productService.deleteProduct(productId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @Override
  public ResponseEntity<ProductDto> addProduct(ProductDto productDto){
    this.productService.addProduct(ProductMapper.INSTANCE.toProduct(productDto));
    return new ResponseEntity<>(productDto,HttpStatus.OK);
  }


}
