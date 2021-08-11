package com.example.ordersapp.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ordersapp.model.Meal;
import com.example.ordersapp.model.Restaurants;
import com.example.ordersapp.model.RestaurantsModel;
import com.example.ordersapp.model.RestaurantsSectionModel;
import com.example.ordersapp.model.Section;
import com.example.ordersapp.network.APIService;
import com.example.ordersapp.network.Retrolnstance;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantSectionListViewModel extends ViewModel {

    private MutableLiveData<List<Section>> restaurantsList;
    private final List<Section> sections = new ArrayList<>();

    public RestaurantSectionListViewModel() {
        restaurantsList = new MutableLiveData<>();
    }

    public MutableLiveData<List<Section>> getRestaurantsList0bserver() {
        return restaurantsList;
    }

    public void makeApiCall(List<Meal> meals) {
        Log.d("restSection", meals.size()+"");

        APIService apiService = Retrolnstance.getRetrofitClient().create(APIService.class);
        Call<RestaurantsSectionModel> call = apiService.getRestaurantsSectionList();
        call.enqueue(new Callback<RestaurantsSectionModel>() {
            @Override
            public void onResponse(Call<RestaurantsSectionModel> call, Response<RestaurantsSectionModel> response) {
                if (response.body().isStatus()) {
                    Log.d("restSection", "true");
                    for (Section s : response.body().getData()) {
                        for (Meal m : meals) {
                            if (s.getId() == m.getSection_id()) {
                                sections.add(s);
                            }
                        }
                    }

                    restaurantsList.postValue(removeDuplicates(sections));

                } else {
                    Log.d("restSection", "faile 1 ");
                }
            }

            @Override
            public void onFailure(Call<RestaurantsSectionModel> call, Throwable t) {
                restaurantsList.postValue(null);

                Log.d("restSection", "faile" + t.getMessage());
            }
        });

    }

    public static ArrayList<Section> removeDuplicates(List<Section> list){
        Set<Section> set = new LinkedHashSet<>(list);
        return new ArrayList<Section>(set);
    }

}
