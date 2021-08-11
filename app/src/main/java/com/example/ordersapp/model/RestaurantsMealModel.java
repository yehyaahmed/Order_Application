package com.example.ordersapp.model;

import java.util.List;

public class RestaurantsMealModel {

    private boolean status;
    private String message;
    private List<Meal> data;

    public RestaurantsMealModel(boolean status, String message, List<Meal> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public RestaurantsMealModel() {

    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Meal> getData() {
        return data;
    }

    public void setData(List<Meal> data) {
        this.data = data;
    }
}
