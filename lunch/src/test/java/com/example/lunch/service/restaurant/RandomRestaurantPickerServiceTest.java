package com.example.lunch.service.restaurant;

import com.example.lunch.bean.ResultInfo;
import com.example.lunch.entity.Restaurant;
import com.example.lunch.repository.RestaurantRepository;
import com.example.lunch.service.RestaurantService;
import com.example.lunch.service.picker.RandomPicker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class RandomRestaurantPickerServiceTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    private Random predictableRandom;

    private RandomPicker<Restaurant> randomPicker;

    @InjectMocks
    private RestaurantService restaurantService;

    @BeforeEach
    public void setUp() {
        predictableRandom = new Random() {
            @Override
            public int nextInt(int bound) {
                return 0;  // Always return the first element
            }
        };
        randomPicker = new RandomPicker<>(predictableRandom);
        restaurantService = new RestaurantService(restaurantRepository, randomPicker);
    }

    @Test
    void testPickRandomRestaurant() {
        List<Restaurant> restaurants = IntStream.range(0, 10)
                .mapToObj(s -> new Restaurant("test-user-" + s,"test-restaurant-" + s , "test-session-" + s))
                .toList();
        Mockito.when(restaurantRepository.findBySessionId(Mockito.anyString())).thenReturn(restaurants);

        ResultInfo result = restaurantService.pickRandomRestaurant("test-session");

        Assertions.assertNotNull(result);
        Assertions.assertEquals("test-restaurant-0", result.getResultSelection());
    }

    @Test
    void testPickRandomEmptyRestaurant() {
        Mockito.when(restaurantRepository.findBySessionId(Mockito.anyString())).thenReturn(new ArrayList<>());
        ResultInfo info = restaurantService.pickRandomRestaurant("test-session");
        Assertions.assertNull(info.getResultSelection());
    }
}
