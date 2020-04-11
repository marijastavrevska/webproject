package com.example.demo.repository;

import com.example.demo.model.Restaurant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
}

