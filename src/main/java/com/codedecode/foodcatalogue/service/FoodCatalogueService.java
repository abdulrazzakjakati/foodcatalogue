package com.codedecode.foodcatalogue.service;

import com.codedecode.foodcatalogue.dto.FoodCataloguePage;
import com.codedecode.foodcatalogue.dto.FoodItemDTO;
import com.codedecode.foodcatalogue.dto.Restaurant;
import com.codedecode.foodcatalogue.entity.FoodItem;
import com.codedecode.foodcatalogue.mapper.FoodItemMapper;
import com.codedecode.foodcatalogue.repository.FoodItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodCatalogueService {

    private final FoodItemRepository foodItemRepository;
    private final FoodItemMapper foodItemMapper;
    private final RestTemplate restTemplate;

    public FoodItemDTO addFoodItem(FoodItemDTO foodItemDTO) {
        var savedItem = foodItemRepository.save(foodItemMapper.foodItemDTOToFoodItem(foodItemDTO));
        return foodItemMapper.foodItemToFoodItemDTO(savedItem);
    }

    public FoodCataloguePage fetchFoodCataloguePageDetails(Long restaurantId) {
        // Implementation to fetch food catalogue page details
        List<FoodItemDTO> foodItemDTOList = fetchFoodItemList(restaurantId);
        Restaurant restaurant = fetchRestaurantDetailsMS(restaurantId); // Placeholder for fetching restaurant details

        return createFoodCataloguePage(foodItemDTOList, restaurant);
    }

    private FoodCataloguePage createFoodCataloguePage(List<FoodItemDTO> foodItemDTOList,
                                                      Restaurant restaurant) {
        return new FoodCataloguePage(foodItemDTOList, restaurant);
    }

    private Restaurant fetchRestaurantDetailsMS(Long restaurantId) {
        return restTemplate.getForObject("http://RESTAURANT-SERVICE/restaurants/" + restaurantId, Restaurant.class);
    }

    private List<FoodItemDTO> fetchFoodItemList(Long restaurantId) {
        List<FoodItem> foodItems = foodItemRepository.findByRestaurantId(restaurantId);
        return foodItems.stream()
                .map(foodItemMapper::foodItemToFoodItemDTO)
                .toList();
    }
}
