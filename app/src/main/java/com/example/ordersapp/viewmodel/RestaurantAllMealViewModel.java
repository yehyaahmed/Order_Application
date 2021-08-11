package com.example.ordersapp.viewmodel;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ordersapp.DBHelper;
import com.example.ordersapp.adapter.RestaurantsAdapter;
import com.example.ordersapp.model.FavoriteItem;
import com.example.ordersapp.model.Meal;
import com.example.ordersapp.model.RestaurantsMealModel;
import com.example.ordersapp.network.APIService;
import com.example.ordersapp.network.Retrolnstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantAllMealViewModel extends ViewModel {

    private MutableLiveData<List<Meal>> mealList;
    private Context c;

    List<FavoriteItem> favoriteItems = new ArrayList<>();
    private final List<Meal> list = new ArrayList<>();

    public RestaurantAllMealViewModel() {
        mealList = new MutableLiveData<>();
    }

    public MutableLiveData<List<Meal>> getRestaurantsList0bserver() {
        return mealList;
    }

    public void makeApiCall(Context context) {
        c = context;

        getFavMeal();

        APIService apiService = Retrolnstance.getRetrofitClient().create(APIService.class);
        Call<RestaurantsMealModel> call = apiService.getRestaurantsMealList();
        call.enqueue(new Callback<RestaurantsMealModel>() {
            @Override
            public void onResponse(Call<RestaurantsMealModel> call, Response<RestaurantsMealModel> response) {
                if (response.body().isStatus()) {

                    if (favoriteItems != null && favoriteItems.size() >= 0) {
                        for (Meal m : response.body().getData()) {
                            for (FavoriteItem f : favoriteItems) {
                                if (m.getId() == f.getId()) {
                                    list.add(m);
                                }
                                mealList.postValue(list);

                            }
                        }
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

    private void getFavMeal() {

        DBHelper dbHelper = new DBHelper(c);

        Cursor cursor = dbHelper.getAllMeals();

        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndex("id"));
                    String isFavorite = cursor.getString(cursor.getColumnIndex("isFavorite"));
                    if (isFavorite.equals("like")) {
                        favoriteItems.add(new FavoriteItem(id, isFavorite));
                    }
                } while (
                        cursor.moveToNext()
                );
            }
        }
    }

}
