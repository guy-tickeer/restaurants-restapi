package com.example.demo.models;

import java.io.Serializable;
import java.util.*;

public class RestaurantsChain implements Serializable {
    String id;
    String timestamp;
    String owner;
    double rating;
    boolean is_deleted;
    List<String> restaurants;
    Map<String ,Double> ratings;


    public RestaurantsChain(String owner, double rating, String firstRestaurant) {
        this.ratings=new HashMap<>();
        this.id = UUID.randomUUID().toString();
        this.timestamp = DateConverter.dateToString(new Date());
        this.owner = owner;
        this.rating = rating;
        this.is_deleted = false;
        this.restaurants = new LinkedList<>();
        this.restaurants.add(firstRestaurant);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public double getRating() {
        return rating;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public List<String> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<String> restaurants) {
        this.restaurants = restaurants;
    }

    public String getOwner() {
        return owner;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void removeRestaurant(Restaurant restaurant) {
        restaurants.remove(restaurant.getRestaurant_name());
        ratings.remove(restaurant.getRestaurant_name());
        calcRating();
        updateTimeStamp();
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant.getRestaurant_name());
        ratings.put(restaurant.getRestaurant_name(),restaurant.getRating());
        calcRating();
        updateTimeStamp();
    }
    public void calcRating(){
        double sum=0;
        for(double val:ratings.values()){
            sum+=val;
        }
        setRating(sum/ratings.size());
    }

    public boolean contains(Restaurant restaurant) {
        return restaurants.contains(restaurant.getRestaurant_name());
    }


    public void updateTimeStamp() {
        this.timestamp = DateConverter.dateToString(new Date());
    }

}
