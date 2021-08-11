package com.example.ordersapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import com.example.ordersapp.R;
import com.example.ordersapp.adapter.RestaurantsAdapter;
import com.example.ordersapp.model.Restaurants;
import com.example.ordersapp.model.RestaurantsModel;
import com.example.ordersapp.viewmodel.RestaurantListViewModel;

import java.util.ArrayList;
import java.util.List;

public class AllRestaurantsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_restaurants);


    }
}