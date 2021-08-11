package com.example.ordersapp.fragment;

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
import android.widget.TextView;

import com.example.ordersapp.R;
import com.example.ordersapp.adapter.RestaurantsAdapter;
import com.example.ordersapp.adapter.RestaurantsMealAdapter;
import com.example.ordersapp.adapter.RestaurantsSectionAdapter;
import com.example.ordersapp.model.Meal;
import com.example.ordersapp.model.Restaurants;
import com.example.ordersapp.viewmodel.RestaurantListViewModel;
import com.example.ordersapp.viewmodel.RestaurantMealViewModel;

import java.util.ArrayList;
import java.util.List;

public class RestaurantMealsFragment extends Fragment {

    TextView resturantNameInMealFragmentTV, sectionNameInMealFragmentTV;
    RecyclerView recyclerViewInMealFragment;

    private List<Meal> mealList;
    private RestaurantsMealAdapter mealAdapter;
    private RestaurantMealViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_restaurant_meals, container, false);

        init(view);

        resturantNameInMealFragmentTV.setText(RestaurantsAdapter.restaurant_name);
        sectionNameInMealFragmentTV.setText(RestaurantsSectionAdapter.restaurant_section_name);

        viewModel = ViewModelProviders.of(this).get(RestaurantMealViewModel.class);
        viewModel.getRestaurantsList0bserver().observe((LifecycleOwner) view.getContext(), new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {

                for (Meal m : meals) {
                    if (m.getSection_id() == RestaurantsSectionAdapter.restaurant_section_id) {
                        mealList.add(m);
                    }
                    mealAdapter.setRestaurantsModelList(mealList);
                }
            }
        });


        viewModel.makeApiCall();


        return view;
    }

    private void init(View view) {

        mealList = new ArrayList<>();

        resturantNameInMealFragmentTV = view.findViewById(R.id.resturantNameInMealFragmentTV);
        sectionNameInMealFragmentTV = view.findViewById(R.id.sectionNameInMealFragmentTV);

        recyclerViewInMealFragment = view.findViewById(R.id.recyclerViewInMealFragment);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerViewInMealFragment.setLayoutManager(manager);

        mealAdapter = new RestaurantsMealAdapter(view.getContext(), mealList);
        recyclerViewInMealFragment.setAdapter(mealAdapter);


    }
}