package com.example.demo.DataAccessLayer;

import com.example.demo.models.Restaurant;
import com.example.demo.models.RestaurantsChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component
public class RestaurantsChainsDAL {
    @Autowired
    private RestaurantsDAL _restaurantsDAL;
    private Map<String, RestaurantsChain> restaurantsChains;

    public RestaurantsChainsDAL() {
        restaurantsChains = new HashMap<>();
    }

    public RestaurantsDAL get_restaurantsDAL() {
        return _restaurantsDAL;
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

    public RestaurantsChain addRestaurantToChain(Restaurant restaurant) {
        RestaurantsChain chainToUpdate = getByOwner(restaurant.getOwner_name());
        if (chainToUpdate == null) {
            return this.createChain(restaurant);
        }
        chainToUpdate.addRestaurant(restaurant);
        return chainToUpdate;
    }

    public void updateRestaurantInChain(Restaurant restaurant, RestaurantsChain chain) {
        chain.removeRestaurant(restaurant);
        chain.addRestaurant(restaurant);
    }

}
