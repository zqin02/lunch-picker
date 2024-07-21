package com.example.lunch.service.restaurant;

import com.example.lunch.bean.RestaurantInfo;
import com.example.lunch.entity.Restaurant;
import com.example.lunch.repository.RestaurantRepository;
import com.example.lunch.service.RestaurantService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RestaurantServiceTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private RestaurantService restaurantService;



    @Test
    void testSubmitRestaurant() {
        String name = "Test Restaurant";
        String userId = "usert";
        String sessionId = "session key";
        RestaurantInfo restaurantInfo = new RestaurantInfo(sessionId,name,userId);
        Restaurant restaurant = new Restaurant(restaurantInfo);
        Mockito.when(restaurantRepository.save(Mockito.any(Restaurant.class))).thenReturn(restaurant);

        Restaurant submittedRestaurant = restaurantService.submitRestaurant(restaurantInfo);

        Mockito.verify(restaurantRepository, Mockito.times(1)).save(Mockito.any(Restaurant.class));

        Assertions.assertEquals(name, submittedRestaurant.getRestaurantName());
        Assertions.assertEquals(userId, submittedRestaurant.getUser());
        Assertions.assertEquals(sessionId, submittedRestaurant.getSessionId());
    }
}
