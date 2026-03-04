package com.codedecode.foodcatalogue.dto;

public record FoodItemDTO(Long id,
                          String itemName,
                          String itemDescription,
                          Boolean isVeg,
                          Long price,
                          Long restaurantId,
                          Integer quantity) {
}
