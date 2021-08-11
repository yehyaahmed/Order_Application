package com.example.ordersapp.network;

import com.example.ordersapp.model.LoginResponse;
import com.example.ordersapp.model.RestaurantsMealModel;
import com.example.ordersapp.model.RestaurantsModel;
import com.example.ordersapp.model.RestaurantsSectionModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {

    @GET("restaurants")
    Call<RestaurantsModel> getRestaurantsList();

    @GET("sections")
    Call<RestaurantsSectionModel> getRestaurantsSectionList();

    @GET("meals")
    Call<RestaurantsMealModel> getRestaurantsMealList();

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> userLogin(
            @Field("phoneNumber") String phoneNumber,
            @Field("password") String password
    );

}
