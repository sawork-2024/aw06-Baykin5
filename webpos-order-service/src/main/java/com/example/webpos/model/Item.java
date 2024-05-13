package com.example.webpos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


// 保存所有item

@Table(name = "items")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "pid")
    public int pid;

    @Column(name = "quantity")
    public int quantity;


    public void setProductId(int productId) { this.pid = pid; }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }


}
