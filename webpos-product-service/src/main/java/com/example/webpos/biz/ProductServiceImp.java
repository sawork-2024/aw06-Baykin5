package com.example.webpos.biz;

import com.example.webpos.repository.ProductRepository;
import com.example.webpos.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;


@Component
public class ProductServiceImp implements ProductService {


    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;
    private ProductRepository productRepository;


    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> products() {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("CircuitBreaker");
        return circuitBreaker.run(() -> this.productRepository.findAll(),throwable -> getDefaultProduct());

    }

    public List<Product> getDefaultProduct(){
        List<Product> res = new ArrayList<>();
        res.add(new Product(1,"ERROR",0,"ERROR",0));
        return res;
    }

    @Override
    public void addProduct(Product product){
        this.productRepository.save(product);
    }

    @Override
    public void deleteProduct(int productId){
        this.productRepository.deleteById(productId);
    }

    @Override 
    public Product findProductById(int id){
        return productRepository.findById(id).orElse(null);
    }


}
