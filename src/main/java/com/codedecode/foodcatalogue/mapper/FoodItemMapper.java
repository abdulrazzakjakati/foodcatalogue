package com.codedecode.foodcatalogue.mapper;

import com.codedecode.foodcatalogue.dto.FoodItemDTO;
import com.codedecode.foodcatalogue.entity.FoodItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FoodItemMapper {

    FoodItemDTO foodItemToFoodItemDTO(FoodItem foodItem);
    FoodItem foodItemDTOToFoodItem(FoodItemDTO foodItemDTO);
}