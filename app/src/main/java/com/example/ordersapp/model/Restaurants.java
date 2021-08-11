package com.example.ordersapp.model;

public class Restaurants {

    private int id;
    private String name;
    private String description;
    private String location;
    private String image_link;

    public Restaurants(int id, String name, String description, String location, String image_link) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.image_link = image_link;
    }

    public Restaurants() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }
}
