package com.codedecode.foodcatalogue.dto;

import java.util.List;

public record FoodCataloguePage(
        List<FoodItemDTO> foodItemsList,
        Restaurant restaurant
) {
}
