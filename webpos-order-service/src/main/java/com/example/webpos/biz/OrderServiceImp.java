package com.example.webpos.biz;

import com.example.webpos.repository.ItemRepository;
import com.example.webpos.model.Item;
import org.openapitools.model.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import java.util.List;


@Component
public class OrderServiceImp implements OrderService {


    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;

    private ItemRepository itemRepository;

    public OrderServiceImp(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> items(){
        return this.itemRepository.findAll();
    }


    @Override
    public Item findItemById(int id){
        return itemRepository.findById(id).orElse(null);
    }

    @Override
    public void addItem(Item item){
        this.itemRepository.save(item);
    }

    @Override
    public void deleteItem(int id){ this.itemRepository.deleteById(id);}

    @Override
    public double getTotal(){
        List<Item> items = items();
        // 从product-server那里获取每个product的价格数据
        double sum = 0;
        for (Item item:items){
            String url = "http://PRODUCT-SERVICE/products/" + item.pid;
            System.out.println(url);
            ResponseEntity<ProductDto> response =
                    restTemplate.getForEntity(url,ProductDto.class);
            sum += item.quantity*response.getBody().getPrice();
        }

        return sum;
    }

//    public void addItem(int productId,int quantity){
//
//        List<Item> items = this.itemRepository.findByproductId(productId);
//        if (items.size()>0){
//            Item item = items.get(0);
//            item.setQuantity(item.quantity+quantity);
//            this.itemRepository.save(item);
//        }
//        else{
//            Item item = new Item();
//            item.setProductId(productId);
//            item.setQuantity(quantity);
//            this.itemRepository.save(item);
//        }
//
//    }
//
//    public void deleteItem(int productId,int quantity){
//        List<Item> items = this.itemRepository.findByproductId(productId);
//        if (items.size()>0){
//            Item item = items.get(0);
//            item.setQuantity(item.quantity-quantity);
//            if (item.quantity > 0)
//                this.itemRepository.save(item);
//            else
//                this.itemRepository.delete(item);
//        }
//    }
//
//    public void deleteItem(int productId){
//        List<Item> items = this.itemRepository.findByproductId(productId);
//        if (items.size()>0){
//            this.itemRepository.delete(items.get(0));
//        }
//    }
//
//    public void clearItems(){
//        this.itemRepository.deleteAll();
//    }

//    public double getTotal(){
//        double total = 0;
//        for (Item item:this.itemRepository.findAll()){
//            total += item.quantity* item.product.price;
//        }
//        return total;
//    }
}
