package com.example.lunch.service.picker;

import java.util.List;
import java.util.Random;

public class RandomPicker<T> implements IPicker<T>{
    private final Random random;
    public RandomPicker() {
        this.random = new Random();
    }
    public RandomPicker(Random random) {
        this.random = random;
    }

    @Override
    public T select(List<T> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("list cannot be null or empty");
        }
        return items.get(random.nextInt(items.size()));
    }
}
