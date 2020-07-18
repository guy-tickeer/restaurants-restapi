package com.example.demo.DataStorage;

import com.example.demo.models.Restaurant;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RestaurantsStorage {
    private Map<String,Restaurant> restaurants;


    public RestaurantsStorage() {
        this.restaurants = new HashMap<>();
    }


    public void create(Restaurant restaurant) {
        restaurants.put(restaurant.getRestaurant_name(),restaurant);
    }

    public void update(Restaurant restaurant) {
        restaurants.replace(restaurant.getRestaurant_name(),restaurant);

    }

    public boolean contains(String restaurantName) {
        return restaurants.containsKey(restaurantName);
    }


    public Restaurant getByName(String restaurantName){
        return restaurants.get(restaurantName);
    }

}
