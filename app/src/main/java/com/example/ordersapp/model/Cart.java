package com.example.ordersapp.model;

public class Cart {

    private String image;
    private String name;
    private String cartprice;
    private String cartNumber;

    public Cart(String image, String name, String cartprice, String cartNumber) {
        this.image = image;
        this.name = name;
        this.cartprice = cartprice;
        this.cartNumber = cartNumber;
    }

    public Cart() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCartprice() {
        return cartprice;
    }

    public void setCartprice(String cartprice) {
        this.cartprice = cartprice;
    }

    public String getCartNumber() {
        return cartNumber;
    }

    public void setCartNumber(String cartNumber) {
        this.cartNumber = cartNumber;
    }
}
