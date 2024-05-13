package com.example.webpos.biz;

import com.example.webpos.repository.ItemRepository;
import com.example.webpos.model.Item;
import org.springframework.stereotype.Component;


import java.util.List;


@Component
public class ItemServiceImp implements ItemService {

    private ItemRepository itemRepository;
    // private CartRepository cartRepository;

    public ItemServiceImp(ItemRepository itemRepository) {
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
