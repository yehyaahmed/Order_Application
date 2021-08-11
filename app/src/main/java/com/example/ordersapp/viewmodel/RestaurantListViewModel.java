package com.example.ordersapp.viewmodel;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ordersapp.model.Restaurants;
import com.example.ordersapp.model.RestaurantsModel;
import com.example.ordersapp.network.APIService;
import com.example.ordersapp.network.Retrolnstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantListViewModel extends ViewModel {

    private MutableLiveData<List<Restaurants>> restaurantsList;

    public RestaurantListViewModel() {
        restaurantsList = new MutableLiveData<>();
    }

    public MutableLiveData<List<Restaurants>> getRestaurantsList0bserver() {
        return restaurantsList;
    }

    public void makeApiCall() {
        APIService apiService = Retrolnstance.getRetrofitClient().create(APIService.class);
        Call<RestaurantsModel> call = apiService.getRestaurantsList();
        call.enqueue(new Callback<RestaurantsModel>() {
            @Override
            public void onResponse(Call<RestaurantsModel> call, Response<RestaurantsModel> response) {
                RestaurantsModel model = response.body();
                if (model.isStatus()) {
                    Log.d("restaurants", "true");
                    restaurantsList.postValue(model.getData().getRestaurants());
                    Log.d("restaurants", model.getData().getRestaurants().get(0).getName() + "");
                } else {
                    Log.d("restaurants", "false");
                }
            }

            @Override
            public void onFailure(Call<RestaurantsModel> call, Throwable t) {
                restaurantsList.postValue(null);
                Log.d("restaurants", "false2" + t.getMessage().toString() + "");

            }
        });

    }
}
