package com.example.ordersapp.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ordersapp.adapter.RestaurantsAdapter;
import com.example.ordersapp.adapter.RestaurantsMealAdapter;
import com.example.ordersapp.adapter.RestaurantsSectionAdapter;
import com.example.ordersapp.model.Meal;
import com.example.ordersapp.model.RestaurantsMealModel;
import com.example.ordersapp.network.APIService;
import com.example.ordersapp.network.Retrolnstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantMealDetailsViewModel extends ViewModel {

    private MutableLiveData<List<Meal>> mealList;
    private final List<Meal> list = new ArrayList<>();

    public RestaurantMealDetailsViewModel() {
        mealList = new MutableLiveData<>();
    }

    public MutableLiveData<List<Meal>> getRestaurantsList0bserver() {
        return mealList;
    }

    public void makeApiCall() {
        APIService apiService = Retrolnstance.getRetrofitClient().create(APIService.class);
        Call<RestaurantsMealModel> call = apiService.getRestaurantsMealList();
        call.enqueue(new Callback<RestaurantsMealModel>() {
            @Override
            public void onResponse(Call<RestaurantsMealModel> call, Response<RestaurantsMealModel> response) {
                if (response.body().isStatus()) {
                    Log.d("meal", "true");
                    for (Meal m : response.body().getData()) {

                        if (m.getId() == RestaurantsMealAdapter.restaurant_meal_id) {
                            list.add(m);
                        }

                        mealList.postValue(list);

                    }
                } else {
                    Log.d("meal", "false 1");
                }
            }

            @Override
            public void onFailure(Call<RestaurantsMealModel> call, Throwable t) {
                Log.d("meal", "false" + t.getMessage());
            }
        });

    }
}
