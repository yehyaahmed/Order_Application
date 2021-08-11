package com.example.ordersapp.model;

import java.util.List;

public class RestaurantsData {

    private List<Restaurants> restaurants;

    public RestaurantsData(List<Restaurants> restaurants) {
        this.restaurants = restaurants;
    }

    public RestaurantsData() {
    }

    public List<Restaurants> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurants> restaurants) {
        this.restaurants = restaurants;
    }
}
