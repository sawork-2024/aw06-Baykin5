package com.example.webpos.mapper;

import java.util.Collection;

import com.example.webpos.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.openapitools.model.ItemDto;


@Mapper
public interface ItemMapper {
  ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);
  ItemDto toItemDto(Item Item);

  Collection<ItemDto> toItemsDto(Collection<Item> pets);

  Collection<Item> toItems(Collection<ItemDto> pets);

  Item toItem(ItemDto ItemDto);

}
