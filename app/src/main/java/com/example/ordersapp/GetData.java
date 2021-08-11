package com.example.ordersapp;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.ordersapp.model.Restaurants;
import com.example.ordersapp.model.RestaurantsModel;
import com.example.ordersapp.network.APIService;
import com.example.ordersapp.network.Retrolnstance;
import com.example.ordersapp.viewmodel.RestaurantListViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class GetData {
    public static boolean getStatus = false;

    public static void getData() {

        APIService apiService = Retrolnstance.getRetrofitClient().create(APIService.class);
        Call<RestaurantsModel> call = apiService.getRestaurantsList();
        call.enqueue(new Callback<RestaurantsModel>() {
            @Override
            public void onResponse(Call<RestaurantsModel> call, Response<RestaurantsModel> response) {
                RestaurantsModel model = response.body();
                if (model.isStatus()) {
                    getStatus = true;
                } else {
                    getStatus = false;
                }
            }

            @Override
            public void onFailure(Call<RestaurantsModel> call, Throwable t) {
                getStatus = false;
            }
        });
    }

}
