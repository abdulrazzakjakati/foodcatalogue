package com.codedecode.foodcatalogue.controller;

import com.codedecode.foodcatalogue.dto.FoodCataloguePage;
import com.codedecode.foodcatalogue.dto.FoodItemDTO;
import com.codedecode.foodcatalogue.dto.Restaurant;
import com.codedecode.foodcatalogue.service.FoodCatalogueService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FoodItemControllerTest {

    @InjectMocks
    private FoodItemController foodItemController;

    @Mock
    private FoodCatalogueService foodItemService;


    @Test
    void testAddFoodItem() {
        // Implement test logic for addFoodItem method
        //Arrange
        FoodItemDTO foodItemDTO = new FoodItemDTO(1L, "Pizza",
                "Delicious cheese pizza", true, 90L, 1L, 10);
        //Act
        when(foodItemService.addFoodItem(foodItemDTO)).thenReturn(foodItemDTO);

        ResponseEntity<FoodItemDTO> foodItemDTOResponseEntity = foodItemController.addFoodItem(foodItemDTO);

        //Assert
        verify(foodItemService, times(1)).addFoodItem(foodItemDTO);
        assert foodItemDTOResponseEntity.getStatusCode() == HttpStatus.CREATED;
        assertEquals(foodItemDTO, foodItemDTOResponseEntity.getBody());
    }

    @Test
     void testFetchRestaurantWithFoodMenu() {
        // Implement test logic for fetchRestaurantWithFoodMenu method
        //Arrange
        long restaurantId = 1L;
        FoodItemDTO foodItemDTO = new FoodItemDTO(1L, "Pizza", "Delicious cheese pizza", true, 90L, 1L, 10);
        Restaurant restaurant = new Restaurant(1L, "Restaurant A", "Address A", "Cuisine A", "Description A");
        FoodCataloguePage foodCataloguePage = new FoodCataloguePage(List.of(foodItemDTO), restaurant);
        when(foodItemService.fetchFoodCataloguePageDetails(restaurantId)).thenReturn(foodCataloguePage);

        //Act
        ResponseEntity<FoodCataloguePage> responseEntity = foodItemController.fetchRestaurantWithFoodMenu(restaurantId);

        //Assert
        verify(foodItemService, times(1)).fetchFoodCataloguePageDetails(restaurantId);
        assert responseEntity.getStatusCode() == HttpStatus.OK;
        assertEquals(foodCataloguePage, responseEntity.getBody());
     }
}
