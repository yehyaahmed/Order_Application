package com.example.ordersapp.model;

public class FavoriteItem {

    private int id;
    private String isFavorite;

    public FavoriteItem(int id, String isFavorite) {
        this.id = id;
        this.isFavorite = isFavorite;
    }

    public FavoriteItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(String isFavorite) {
        this.isFavorite = isFavorite;
    }
}
