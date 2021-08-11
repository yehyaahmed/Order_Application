package com.example.ordersapp.model;

import java.util.List;

public class RestaurantsModel {

    private boolean status;
    private String message;
    private RestaurantsData data;

    public RestaurantsModel(boolean status, String message, RestaurantsData data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public RestaurantsModel() {
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

    public RestaurantsData getData() {
        return data;
    }

    public void setData(RestaurantsData data) {
        this.data = data;
    }
}
