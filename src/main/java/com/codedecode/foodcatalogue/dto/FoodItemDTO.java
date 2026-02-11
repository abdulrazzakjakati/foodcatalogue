package com.codedecode.foodcatalogue.dto;

public record FoodItemDTO(Long id,
                          String itemName,
                          String itemDescription,
                          Boolean isVeg,
                          Long price,
                          Integer restaurantId,
                          Integer quantity) {
}
