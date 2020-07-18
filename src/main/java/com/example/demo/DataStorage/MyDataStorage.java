package com.example.demo.DataStorage;

import com.example.demo.models.Restaurant;
import com.example.demo.models.RestaurantsChain;
import org.springframework.stereotype.Component;

@Component
public class MyDataStorage {
    private RestaurantsStorage restaurantsStorage;
    private RestaurantsChainsStorage restaurantsChainsStorage;

    public MyDataStorage(RestaurantsStorage restaurantsStorage, RestaurantsChainsStorage restaurantsChainsStorage) {
        this.restaurantsStorage = restaurantsStorage;
        this.restaurantsChainsStorage = restaurantsChainsStorage;
    }

    //Restaurants//

    public boolean isRestaurantExists(String restaurantName) {
        return restaurantsStorage.contains(restaurantName);
    }

    public Restaurant getRestaurantByName(String restaurantName) {
        return restaurantsStorage.getByName(restaurantName);
    }

    public void createRestaurant(Restaurant restaurant) {
        restaurantsStorage.create(restaurant);
    }

    public void updateRestaurant(Restaurant restaurant) {
        restaurantsStorage.update(restaurant);
    }

    //Restaurants Chain//

    public RestaurantsChain getChainByOwner(String ownerName) {
        return restaurantsChainsStorage.getByOwner(ownerName);
    }

    public RestaurantsChain getChainByRestaurant(Restaurant restaurant) {
        return restaurantsChainsStorage.getByRestaurant(restaurant);
    }

    public void removeRestaurantFromChain(Restaurant restaurant, RestaurantsChain chain) {
        restaurantsChainsStorage.removeRestaurantFromChain(restaurant, chain);
    }

    public void updateRestaurantInExistingChain(Restaurant restaurant, RestaurantsChain chain) {
        restaurantsChainsStorage.updateRestaurantInChain(restaurant, chain);
    }

    public RestaurantsChain addRestaurantToChain(Restaurant restaurant) {
        RestaurantsChain chainToUpdate = this.getChainByOwner(restaurant.getOwner_name());
        if (chainToUpdate == null) {
            return restaurantsChainsStorage.createChain(restaurant);
        }
        restaurantsChainsStorage.addRestaurantToChain(restaurant, chainToUpdate);
        return chainToUpdate;
    }

}
