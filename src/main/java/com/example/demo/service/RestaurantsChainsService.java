package com.example.demo.service;


import com.example.demo.DataStorage.MyDataStorage;
import com.example.demo.models.Restaurant;
import com.example.demo.models.RestaurantsChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class RestaurantsChainsService {

    @Autowired
    private MyDataStorage myDataStorage;

    public List<RestaurantsChain> createOrUpdate(String restaurantName) {
        List<RestaurantsChain> chainsInChange = new LinkedList<>();
        Restaurant restaurant = myDataStorage.getRestaurantByName(restaurantName);
        RestaurantsChain chainIncludeRestaurant = myDataStorage.getChainByRestaurant(restaurant);


        if (restaurant.isIs_deleted()) {
            if (chainIncludeRestaurant != null) {
                myDataStorage.removeRestaurantFromChain(restaurant, chainIncludeRestaurant);
                chainsInChange.add(chainIncludeRestaurant);
                return chainsInChange;
            }
            return null;
        }

        if (chainIncludeRestaurant == null) {
            chainsInChange.add(myDataStorage.addRestaurantToChain(restaurant));
            return chainsInChange;
        }

        if (!chainIncludeRestaurant.getOwner().equals(restaurant.getOwner_name())){
            myDataStorage.removeRestaurantFromChain(restaurant, chainIncludeRestaurant);
            chainsInChange.add(chainIncludeRestaurant);
            chainsInChange.add(myDataStorage.addRestaurantToChain(restaurant));
            return chainsInChange;
        }
        if(chainIncludeRestaurant.getOwner().equals(restaurant.getOwner_name())){
            myDataStorage.updateRestaurantInExistingChain(restaurant,chainIncludeRestaurant);
            chainsInChange.add(chainIncludeRestaurant);
            return chainsInChange;
        }
        return null;
    }

    public boolean isRestaurantExist(String restaurantName) {
        return myDataStorage.isRestaurantExists(restaurantName);
    }
}
