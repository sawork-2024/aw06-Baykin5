package com.example.webpos.rest;

import java.util.ArrayList;
import java.util.List;

import com.example.webpos.model.Item;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.webpos.biz.ItemService;
import com.example.webpos.mapper.ItemMapper;
import com.example.webpos.rest.api.ItemsApi;
import com.example.webpos.rest.dto.ItemDto;

@RestController
public class ItemRestController implements ItemsApi{
  ItemService itemService;
  ItemMapper itemMapper;

  public ItemRestController(ItemService itemService, ItemMapper itemMapper){
    this.itemService = itemService;
    this.itemMapper = itemMapper;
  }

  public ResponseEntity<ItemDto> getItem(int itemId){
    ItemDto item = itemMapper.toItemDto(this.itemService.findItemById(itemId));
    if (item ==null){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(item,HttpStatus.OK);
  }

  public ResponseEntity<List<ItemDto>> listItems(){
    List<ItemDto> items = new ArrayList<>(itemMapper.toItemsDto(itemService.items()));
    if (items.isEmpty()){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(items,HttpStatus.OK);
  }

  public ResponseEntity<ItemDto> updateItem(Integer itemId, ItemDto itemDto){
    Item currentItem = this.itemService.findItemById(itemId);
    if (currentItem == null){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    currentItem.setProductId(itemDto.getPid());
    currentItem.setQuantity(itemDto.getQuantity());

    this.itemService.addItem(currentItem);
    return new ResponseEntity<>(itemMapper.toItemDto(currentItem),HttpStatus.NO_CONTENT);

  }


  @Override
  public ResponseEntity<ItemDto> deleteItem(Integer itemId){
    Item Item = this.itemService.findItemById(itemId);
    if (Item == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    this.itemService.deleteItem(itemId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @Override
  public ResponseEntity<ItemDto> addItem(ItemDto itemDto){
    this.itemService.addItem(itemMapper.toItem(itemDto));
    return new ResponseEntity<>(itemDto,HttpStatus.OK);
  }

  
}
