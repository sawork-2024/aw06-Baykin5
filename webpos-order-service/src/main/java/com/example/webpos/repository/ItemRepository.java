package com.example.webpos.repository;

import com.example.webpos.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Integer> {
  // List<Item> findByproductId(int productId);
  
}
