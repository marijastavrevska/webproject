package com.example.demo.service;

import com.example.demo.model.Restaurant;
import java.util.List;
import java.util.Optional;

public interface RestaurantService {
    List<Restaurant> listAllRestaurants();

    Optional<Restaurant> getById(Long id);
    Restaurant saveOrUpdate(Restaurant restaurant);

    void delete(Long id);
}
