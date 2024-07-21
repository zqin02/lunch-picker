package com.example.lunch.service.picker;

import java.util.List;

public interface IPicker<T> {
    T select(List<T> items);
}
