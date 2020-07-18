package com.example.demo.service;

import com.example.demo.DataStorage.MyDataStorage;
import com.example.demo.models.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantsService {

    @Autowired
    private MyDataStorage myDataStorage;


    public String createOrUpdate(Restaurant restaurant){
        Restaurant savedRestaurant=myDataStorage.getRestaurantByName(restaurant.getRestaurant_name());
        if(savedRestaurant==null){
            myDataStorage.createRestaurant(restaurant);
            return  "create";
        }
        if(savedRestaurant.isValidUpdate(restaurant.getTimestamp())){
            myDataStorage.updateRestaurant(restaurant);
            return "update";
        }
        return "no update - update time is too old";
    }
}
