package com.example.demo.service;

import com.example.demo.model.Restaurant;
import com.example.demo.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class RestaurantServiceImpl implements RestaurantService {
    private RestaurantRepository restaurantRepository;
    @Autowired
    public void setRestaurantRepository(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Optional<Restaurant> findById(Long id) {
        return  restaurantRepository.findById(id);
    }

    @Override
    public List<Restaurant> listAllRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurantRepository.findAll().forEach(restaurants::add);
        return restaurants;
    }

    @Override
    public Optional<Restaurant> getById(Long id) {
        return restaurantRepository.findById(id);
    }

    @Override
    public Restaurant saveOrUpdate(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
        return restaurant;
    }

    @Override
    public void delete(Long id) {
        restaurantRepository.deleteById(id);
    }
}
