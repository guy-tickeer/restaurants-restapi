package com.example.demo.DataStorage;

import com.example.demo.models.Restaurant;
import com.example.demo.models.RestaurantsChain;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component
public class RestaurantsChainsStorage {
    private Map<String, RestaurantsChain> restaurantsChains;

    public RestaurantsChainsStorage() {
        restaurantsChains = new HashMap<>();
    }


    public RestaurantsChain getByOwner(String ownerName) {
        return restaurantsChains.get(ownerName);
    }

    public RestaurantsChain getByRestaurant(Restaurant restaurant) {
        Iterator<Map.Entry<String, RestaurantsChain>> it = restaurantsChains.entrySet().iterator();
        Map.Entry<String, RestaurantsChain> chainEntry;
        RestaurantsChain currentChain;
        while (it.hasNext()) {
            chainEntry = it.next();
            currentChain = chainEntry.getValue();
            if (currentChain.contains(restaurant)) {
                return currentChain;
            }
        }
        return null;

    }

    public RestaurantsChain createChain(Restaurant restaurant) {
        RestaurantsChain newChain = new RestaurantsChain(restaurant.getOwner_name(), restaurant.getRating(), restaurant.getRestaurant_name());
        restaurantsChains.put(newChain.getOwner(), newChain);
        return newChain;
    }

    public void removeRestaurantFromChain(Restaurant restaurant, RestaurantsChain chain) {
        chain.removeRestaurant(restaurant);
    }

    public void addRestaurantToChain(Restaurant restaurant, RestaurantsChain chain) {
        chain.addRestaurant(restaurant);
    }

    public boolean isRestaurantInChain(Restaurant restaurant, RestaurantsChain chain) {
        return chain.contains(restaurant);
    }

    public void updateRestaurantInChain(Restaurant restaurant, RestaurantsChain chain) {
        chain.removeRestaurant(restaurant);
        chain.addRestaurant(restaurant);
    }

}
