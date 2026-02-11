package com.codedecode.foodcatalogue.dto;

public record Restaurant(
        Long id,
        String name,
        String address,
        String city,
        String restaurantDescription
) {
}
