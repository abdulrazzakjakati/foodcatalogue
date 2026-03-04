package com.codedecode.foodcatalogue.service;

import com.codedecode.foodcatalogue.dto.FoodCataloguePage;
import com.codedecode.foodcatalogue.dto.FoodItemDTO;
import com.codedecode.foodcatalogue.dto.Restaurant;
import com.codedecode.foodcatalogue.entity.FoodItem;
import com.codedecode.foodcatalogue.mapper.FoodItemMapper;
import com.codedecode.foodcatalogue.repository.FoodItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FoodCatalogueServiceTest {

    @Mock
    private FoodItemRepository repository;

    @InjectMocks
    private FoodCatalogueService foodCatalogueService;

    @Mock
    private RestTemplate restTemplate;

    private FoodItemMapper foodItemMapper = Mappers.getMapper(FoodItemMapper.class);

    @BeforeEach
    void setup() {
        foodCatalogueService =
                new FoodCatalogueService(repository, foodItemMapper, restTemplate);
    }

    @Test
    void testAddFoodItem() {
        // Arrange
        FoodItemDTO foodItemDTO = new FoodItemDTO(1L, "Pizza", "Delicious cheese pizza",
                true, 500L, 1L, 10);
        FoodItem foodItem = new FoodItem(1L, "Pizza", "Delicious cheese pizza",
                true, 500L, 1L, 10);

        when(repository.save(any(FoodItem.class))).thenReturn(foodItem);

        // Act
        FoodItemDTO result = foodCatalogueService.addFoodItem(foodItemDTO);

        //Assert
        verify(repository, times(1)).save(any(FoodItem.class));
        assertEquals(foodItemMapper.foodItemToFoodItemDTO(foodItem), result);
    }

    @Test
    void testFetchFoodCataloguePageDetails() {
        // Implement test logic for fetchFoodCataloguePageDetails method
        long restaurantId = 1L;
        List<FoodItem> foodItemList = List.of(
                new FoodItem(1L, "Pizza", "Delicious cheese pizza",
                        true, 500L, restaurantId, 10)
        );
        Restaurant restaurant = new Restaurant(restaurantId, "Test Restaurant",
                "Test Address", "Test Cuisine", "Test Description");

        when(repository.findByRestaurantId(restaurantId)).thenReturn(foodItemList);
        when(restTemplate.getForObject(anyString(), eq(Restaurant.class)))
                .thenReturn(restaurant);

        //Act
        FoodCataloguePage foodCataloguePage = foodCatalogueService.fetchFoodCataloguePageDetails(restaurantId);

        //Assert
        verify(repository, times(1)).findByRestaurantId(restaurantId);
        verify(restTemplate, times(1)).getForObject(anyString(), eq(Restaurant.class));

        assertEquals(1, foodCataloguePage.foodItemsList().size());
        assertEquals(restaurant, foodCataloguePage.restaurant());
        assertEquals("Pizza", foodCataloguePage.foodItemsList().get(0).itemName());
        assertEquals("Test Restaurant", foodCataloguePage.restaurant().name());
    }
}
