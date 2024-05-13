package com.example.webpos.mapper;

import java.util.Collection;

import com.example.webpos.model.Item;
import org.mapstruct.Mapper;

import com.example.webpos.rest.dto.ItemDto;

@Mapper
public interface ItemMapper {
  ItemDto toItemDto(Item Item);

  Collection<ItemDto> toItemsDto(Collection<Item> pets);

  Collection<Item> toItems(Collection<ItemDto> pets);

  Item toItem(ItemDto ItemDto);

}
