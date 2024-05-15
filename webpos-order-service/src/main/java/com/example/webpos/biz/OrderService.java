package com.example.webpos.biz;

import com.example.webpos.model.Item;

import java.util.List;

// 业务逻辑层，负责从下层获取数据，封装好处理逻辑供上层使用

public interface OrderService {

    public List<Item> items();

    public Item findItemById(int id);

    public void addItem(Item item);

    public void deleteItem(int id);

    public double getTotal();

//    public void addItem(int productId,int quantity);
//
//    public void deleteItem(int productId,int quantity);
//
//    public void deleteItem(int productId);
//
//    public void clearItems();



}
