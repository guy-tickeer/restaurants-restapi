package com.example.demo.controller;


import com.example.demo.models.RestaurantsChain;
import com.example.demo.service.RestaurantsChainsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping({"/restaurantChain"})
public class RestaurantsChainsController {

    @Autowired
    private RestaurantsChainsService restaurantsChainsService;


    @GetMapping({"", "/"})
    public ResponseEntity<Map<String, Object>> createOrUpdate(@RequestParam String restaurantName) {
        String restaurantNameClear=restaurantName.replaceAll("\\\"|”","");
        if (!restaurantsChainsService.isRestaurantExist(restaurantNameClear)) {
            return ResponseEntity.badRequest().body(Map.of("error", "restaurant does not exist"));
        }
        List<RestaurantsChain> restaurantsChains = restaurantsChainsService.createOrUpdate(restaurantNameClear);
        if(restaurantsChains==null){
            return ResponseEntity.ok(Map.of("error", "trying to delete restaurant not existing in chain"));
        }
        return ResponseEntity.ok(Map.of("restaurantsChains",restaurantsChains));
    }
}
