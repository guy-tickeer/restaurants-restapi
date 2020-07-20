package com.example.demo.service;

import com.example.demo.DataAccessLayer.RestaurantsDAL;
import com.example.demo.models.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantsService {

    @Autowired
    private RestaurantsDAL _restaurantsDAL;

    public String createOrUpdate(Restaurant restaurant){
        Restaurant savedRestaurant= _restaurantsDAL.getByName(restaurant.getRestaurant_name());
        if(savedRestaurant==null){
            _restaurantsDAL.create(restaurant);
            return  "create";
        }
        if(savedRestaurant.isValidUpdate(restaurant.getTimestamp())){
            _restaurantsDAL.update(restaurant);
            return "update";
        }
        return "no update - update time is too old";
    }
}
