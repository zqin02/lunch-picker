package com.example.lunch.service;

import com.example.lunch.bean.RestaurantInfo;
import com.example.lunch.bean.ResultInfo;
import com.example.lunch.entity.Restaurant;
import com.example.lunch.exception.WebSockerException;
import com.example.lunch.repository.RestaurantRepository;
import com.example.lunch.service.picker.IPicker;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final IPicker<Restaurant> randomRestaurantPicker;

    public RestaurantService(RestaurantRepository restaurantRepository,IPicker<Restaurant> randomRestaurantPicker) {
        this.restaurantRepository = restaurantRepository;
        this.randomRestaurantPicker = randomRestaurantPicker;
    }
    public Restaurant submitRestaurant(RestaurantInfo restaurantInfo) {
        validateRestaurantSubmission(restaurantInfo);
        Restaurant restaurant = new Restaurant(restaurantInfo);
        return restaurantRepository.save(restaurant);
    }

    public ResultInfo pickRandomRestaurant(String sessionId) {
        List<Restaurant> restaurants = restaurantRepository.findBySessionId(sessionId);
        if (restaurants.isEmpty()) {
            return new ResultInfo();
        }
        return new ResultInfo(randomRestaurantPicker.select(restaurants).getRestaurantName());
    }
    public void deleteBySessionId(String sessionId)
    {
        restaurantRepository.deleteBySessionId(sessionId);
    }

    public List<String> findRestaurantNameBySessionId(String sessionId) {
        return restaurantRepository.findBySessionId(sessionId)
                .stream().map(Restaurant::getRestaurantName).toList();
    }

    private void validateRestaurantSubmission(RestaurantInfo restaurantInfo) {
        if (restaurantRepository.findBySessionIdAndUser(restaurantInfo.getUuid(), restaurantInfo.getUser()) != null) {
            throw new WebSockerException("You can only submit one entry.", restaurantInfo.getUser());
        }
        if (restaurantRepository.findBySessionIdAndRestaurantName(restaurantInfo.getUuid(), restaurantInfo.getRestaurantName()) != null) {
            throw new WebSockerException("Duplicated restaurant.", restaurantInfo.getUser());
        }
    }
}
