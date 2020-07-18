package com.example.demo.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class Restaurant implements Serializable {
    @NotBlank(message = "must provide restaurant name")
    private String restaurant_name;
    @NotBlank(message = "must provide owner name")
    private String owner_name;
    @NotBlank(message = "must provide timestamp")
    @Pattern(regexp = "[0-9]{4}\\-[0-9]{2}\\-[0-9]{2}T[0-9]{2}\\:[0-9]{2}\\:[0-9]{2}[\\+|\\-][0-9]{2}", message = "invalid date pattern")
    private String timestamp;
    @Min(value = 0, message = "rating must be between 0 to 5")
    @Max(value = 5, message = "rating must be between 0 to 5")
    private Double rating;
    @NotBlank(message = "must provide address")
    private String address;
    private boolean is_deleted;


    public Restaurant(String restaurant_name, String owner_name, String timestamp, Double rating, String address, boolean is_deleted) {
        this.restaurant_name = restaurant_name;
        this.owner_name = owner_name;
        this.timestamp = timestamp;
        this.rating = rating;
        this.address = address;
        this.is_deleted = is_deleted;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }


    public String getOwner_name() {
        return owner_name;
    }


    public String getTimestamp() {
        return timestamp;
    }


    public Double getRating() {
        return rating;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }


    public boolean isValidUpdate(String newDate) {
        return DateConverter.stringToDate(newDate).after(DateConverter.stringToDate(this.timestamp));
    }

}
