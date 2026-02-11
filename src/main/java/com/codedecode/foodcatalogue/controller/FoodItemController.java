package com.codedecode.foodcatalogue.controller;

import com.codedecode.foodcatalogue.dto.FoodCataloguePage;
import com.codedecode.foodcatalogue.dto.FoodItemDTO;
import com.codedecode.foodcatalogue.service.FoodCatalogueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/food-items")
@RequiredArgsConstructor
@CrossOrigin
public class FoodItemController {

    private final FoodCatalogueService foodItemService;

    @PostMapping
    public ResponseEntity<FoodItemDTO> addFoodItem(FoodItemDTO foodItemDTO) {
        FoodItemDTO addedFoodItem = foodItemService.addFoodItem(foodItemDTO);
        return ResponseEntity.status(201).body(addedFoodItem);
    }

    @GetMapping("/catalogue/{restaurantId}")
    public ResponseEntity<FoodCataloguePage> fetchRestaurantWithFoodMenu(@PathVariable Long restaurantId) {
        FoodCataloguePage foodCataloguePage = foodItemService.fetchFoodCataloguePageDetails(restaurantId);
        return new ResponseEntity<>(foodCataloguePage, HttpStatus.OK);
    }

}
