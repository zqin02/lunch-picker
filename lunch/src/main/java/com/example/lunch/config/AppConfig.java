package com.example.lunch.config;

import com.example.lunch.entity.Restaurant;
import com.example.lunch.service.picker.RandomPicker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public RandomPicker<Restaurant> randomRestaurantPicker()
    {
        return new RandomPicker<>();
    }
}
