package com.example.ordersapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.ordersapp.R;
import com.example.ordersapp.adapter.RestaurantsAdapter;
import com.example.ordersapp.model.Restaurants;
import com.example.ordersapp.view.AllRestaurantsActivity;
import com.example.ordersapp.viewmodel.RestaurantListViewModel;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private List<Restaurants> restaurantsList;
    private RestaurantsAdapter restaurantsAdapter;
    private RestaurantListViewModel viewModel;

    RecyclerView allResturantsRecyclerView;
    List<SlideModel> slideModels;
    ImageSlider slider;

    RequestQueue referenceQueue;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        init(view);

        viewModel = ViewModelProviders.of(this).get(RestaurantListViewModel.class);
        viewModel.getRestaurantsList0bserver().observe((LifecycleOwner) view.getContext(), new Observer<List<Restaurants>>() {
            @Override
            public void onChanged(List<Restaurants> restaurants) {
                if (restaurants != null) {
                    restaurantsList = restaurants;
                    restaurantsAdapter.setRestaurantsModelList(restaurants);
                }
            }
        });

        viewModel.makeApiCall();

        slideModels = new ArrayList<>();

        getAds();

        return view;
    }

    private void init(View view) {

        referenceQueue = Volley.newRequestQueue(view.getContext());

        restaurantsList = new ArrayList<>();

        slider = view.findViewById(R.id.slider);

        allResturantsRecyclerView = view.findViewById(R.id.allResturantsRecyclerView);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        allResturantsRecyclerView.setLayoutManager(manager);

        restaurantsAdapter = new RestaurantsAdapter(view.getContext(), restaurantsList);
        allResturantsRecyclerView.setAdapter(restaurantsAdapter);

    }

    private void getAds() {
        String url = "https://order.avocodes.com/api/advertising";
        JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject jsonObject = response.getJSONObject("data");
                            JSONArray jsonArray = jsonObject.getJSONArray("advertising");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                String image_link = object.getString("image");
                                String title = object.getString("title");
                                String image = "https://order.avocodes.com/images/" + image_link;
                                Log.d("testP",title+"");
                                Log.d("testP",image+"");
                                slideModels.add(new SlideModel(image, title, ScaleTypes.FIT));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        slider.setImageList(slideModels, ScaleTypes.FIT);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        referenceQueue.add(request1);

    }


}