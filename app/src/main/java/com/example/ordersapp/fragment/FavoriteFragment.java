package com.example.ordersapp.fragment;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ordersapp.DBHelper;
import com.example.ordersapp.R;
import com.example.ordersapp.adapter.FavoriteMealAdapter;
import com.example.ordersapp.model.FavoriteItem;
import com.example.ordersapp.model.Meal;
import com.example.ordersapp.viewmodel.RestaurantAllMealViewModel;
import com.example.ordersapp.viewmodel.RestaurantMealViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {


    RecyclerView favoriteRecyclerView;

    private List<Meal> mealList;
    private FavoriteMealAdapter favoriteMealAdapter;
    private RestaurantAllMealViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        init(view);

        viewModel = ViewModelProviders.of(this).get(RestaurantAllMealViewModel.class);
        viewModel.getRestaurantsList0bserver().observe((LifecycleOwner) view.getContext(), new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                mealList = meals;
                favoriteMealAdapter.setRestaurantsModelList(meals);
            }
        });

        viewModel.makeApiCall(view.getContext());

        return view;
    }

    private void init(View view) {

        mealList = new ArrayList<>();

        favoriteRecyclerView = view.findViewById(R.id.favoriteRecyclerView);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        favoriteRecyclerView.setLayoutManager(manager);

        favoriteMealAdapter = new FavoriteMealAdapter(view.getContext(), mealList);
        favoriteRecyclerView.setAdapter(favoriteMealAdapter);
    }
}