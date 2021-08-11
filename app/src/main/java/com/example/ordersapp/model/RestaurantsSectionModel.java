package com.example.ordersapp.model;

import java.util.List;

public class RestaurantsSectionModel {

    private boolean status;
    private String message;
    private List<Section> data;

    public RestaurantsSectionModel(boolean status, String message, List<Section> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public RestaurantsSectionModel() {
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

    public List<Section> getData() {
        return data;
    }

    public void setData(List<Section> data) {
        this.data = data;
    }
}
