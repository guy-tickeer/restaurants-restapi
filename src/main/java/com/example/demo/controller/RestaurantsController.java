package com.example.demo.controller;


import com.example.demo.models.Restaurant;
import com.example.demo.models.ValidationErrorsHandler;
import com.example.demo.service.RestaurantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping({"/restaurant"})
public class RestaurantsController {

    @Autowired
    private RestaurantsService restaurantsService;

    @PostMapping({"","/"})
    public ResponseEntity<Map<String, String>> createOrUpdate(@Valid @RequestBody Restaurant restaurant, Errors errors){
        if(errors.hasErrors()){
            return new ResponseEntity(ValidationErrorsHandler.mapErrors(errors), HttpStatus.BAD_REQUEST);
        }
        String action=restaurantsService.createOrUpdate(restaurant);
        return ResponseEntity.ok(Map.of("action", action));
    }

}
