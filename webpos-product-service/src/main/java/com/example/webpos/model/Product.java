package com.example.webpos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

//  保存所有product

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "product_name")
    public String name;

    @Column(name = "price")
    public double price;

    @Column(name = "image")
    public String image;

    @Column(name = "quantity")
    public int quantity;

    public Product(){
        this.id = 0;
        this.name = "null";
        this.price = 0;
        this.image = "null";
        this.quantity = 0;
    }

    public Product(int id, String name, int price, String imgae, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return id + "\t" + name + "\t" + price + "\t" + image;
    }

}
