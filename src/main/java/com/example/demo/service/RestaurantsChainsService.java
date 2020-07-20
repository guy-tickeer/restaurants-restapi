package com.example.demo.service;


import com.example.demo.DataAccessLayer.RestaurantsChainsDAL;
import com.example.demo.models.Restaurant;
import com.example.demo.models.RestaurantsChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class RestaurantsChainsService {

    @Autowired
    private RestaurantsChainsDAL _restaurantsChainsDAL;

    public List<RestaurantsChain> createOrUpdate(String restaurantName) {
        List<RestaurantsChain> chainsInChange = new LinkedList<>();
        Restaurant restaurant = _restaurantsChainsDAL.get_restaurantsDAL().getByName(restaurantName);
        RestaurantsChain chainIncludeRestaurant = _restaurantsChainsDAL.getByRestaurant(restaurant);


        if (restaurant.isIs_deleted()) {
            if (chainIncludeRestaurant != null) {
                _restaurantsChainsDAL.removeRestaurantFromChain(restaurant, chainIncludeRestaurant);
                chainsInChange.add(chainIncludeRestaurant);
                return chainsInChange;
            }
            return null;
        }

        if (chainIncludeRestaurant == null) {
            chainsInChange.add(_restaurantsChainsDAL.addRestaurantToChain(restaurant));
            return chainsInChange;
        }

        if (!chainIncludeRestaurant.getOwner().equals(restaurant.getOwner_name())){
            _restaurantsChainsDAL.removeRestaurantFromChain(restaurant, chainIncludeRestaurant);
            chainsInChange.add(chainIncludeRestaurant);
            chainsInChange.add(_restaurantsChainsDAL.addRestaurantToChain(restaurant));
            return chainsInChange;
        }
        if(chainIncludeRestaurant.getOwner().equals(restaurant.getOwner_name())){
            _restaurantsChainsDAL.updateRestaurantInChain(restaurant,chainIncludeRestaurant);
            chainsInChange.add(chainIncludeRestaurant);
            return chainsInChange;
        }
        return null;
    }

    public boolean isRestaurantExist(String restaurantName) {
        return _restaurantsChainsDAL.get_restaurantsDAL().contains(restaurantName);
    }
}
