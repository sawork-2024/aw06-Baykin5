package com.example.webpos.rest;

import java.util.ArrayList;
import java.util.List;

import com.example.webpos.api.ItemsApi;
import com.example.webpos.model.Item;
import org.openapitools.model.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.webpos.biz.OrderService;
import com.example.webpos.mapper.ItemMapper;



@RestController
public class OrderRestController implements ItemsApi {
  @Autowired
  private OrderService orderService;


  @Override
  public ResponseEntity<ItemDto> getItem(Integer itemId){
    ItemDto item = ItemMapper.INSTANCE.toItemDto(this.orderService.findItemById(itemId));
    if (item ==null){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(item,HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<ItemDto>> listItems(){
    List<ItemDto> items = new ArrayList<>(ItemMapper.INSTANCE.toItemsDto(orderService.items()));
    if (items.isEmpty()){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(items,HttpStatus.OK);
  }

  @Override
  public ResponseEntity<ItemDto> updateItem(Integer itemId, ItemDto itemDto){
    Item currentItem = this.orderService.findItemById(itemId);
    if (currentItem == null){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    currentItem.setProductId(itemDto.getPid());
    currentItem.setQuantity(itemDto.getQuantity());

    this.orderService.addItem(currentItem);
    return new ResponseEntity<>(ItemMapper.INSTANCE.toItemDto(currentItem),HttpStatus.NO_CONTENT);

  }


  @Override
  public ResponseEntity<ItemDto> deleteItem(Integer itemId){
    Item Item = this.orderService.findItemById(itemId);
    if (Item == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    this.orderService.deleteItem(itemId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @Override
  public ResponseEntity<ItemDto> addItem(ItemDto itemDto){
    this.orderService.addItem(ItemMapper.INSTANCE.toItem(itemDto));
    return new ResponseEntity<>(itemDto,HttpStatus.OK);
  }

  @GetMapping("/checkout")
  public ResponseEntity<Double> checkout(){
    return new ResponseEntity<>(this.orderService.getTotal(), HttpStatus.OK);
  }
  
}
