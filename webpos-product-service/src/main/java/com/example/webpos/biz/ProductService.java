package com.example.webpos.biz;

import com.example.webpos.model.Product;

import java.util.List;

// 业务逻辑层，负责从下层获取数据，封装好处理逻辑供上层使用

public interface ProductService {
    public List<Product> products();

    public Product findProductById(int id);

    public void addProduct(Product product);

    public void deleteProduct(int productId);



}
