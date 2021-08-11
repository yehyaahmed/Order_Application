package com.example.ordersapp.fragment;

import android.media.Image;
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
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ordersapp.R;
import com.example.ordersapp.adapter.RestaurantsAdapter;
import com.example.ordersapp.adapter.RestaurantsSectionAdapter;
import com.example.ordersapp.model.Meal;
import com.example.ordersapp.model.Restaurants;
import com.example.ordersapp.model.Section;
import com.example.ordersapp.viewmodel.RestaurantListViewModel;
import com.example.ordersapp.viewmodel.RestaurantMealViewModel;
import com.example.ordersapp.viewmodel.RestaurantSectionListViewModel;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RestaurantSectionFragment extends Fragment {

    private List<Section> sectionList;
    private RestaurantsSectionAdapter sectionAdapter;
    private RestaurantSectionListViewModel viewModel;

    private List<Meal> mealList;
    private RestaurantMealViewModel mealViewModel;

    RecyclerView resyaurantSectionRecyclerView;
    TextView resturantNameInSectionFragmentTV;

    ImageView adsResturantImage1, adsResturantImage2;

    RequestQueue referenceQueue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant_section, container, false);

        init(view);

        resturantNameInSectionFragmentTV.setText(RestaurantsAdapter.restaurant_name);

        mealViewModel = ViewModelProviders.of(this).get(RestaurantMealViewModel.class);
        mealViewModel.getRestaurantsList0bserver().observe((LifecycleOwner) view.getContext(), new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                mealList = meals;

                viewModel = ViewModelProviders.of(RestaurantSectionFragment.this).get(RestaurantSectionListViewModel.class);
                viewModel.getRestaurantsList0bserver().observe((LifecycleOwner) view.getContext(), new Observer<List<Section>>() {
                    @Override
                    public void onChanged(List<Section> sections) {
                        sectionList = sections;
                        sectionAdapter.setRestaurantsModelList(sections);
                    }
                });

                viewModel.makeApiCall(mealList);

            }
        });
        mealViewModel.makeApiCall();

        getAds();

        return view;
    }

    private void init(View view) {

        referenceQueue = Volley.newRequestQueue(view.getContext());

        resturantNameInSectionFragmentTV = view.findViewById(R.id.resturantNameInSectionFragmentTV);

        adsResturantImage1 = view.findViewById(R.id.adsResturantImage1);
        adsResturantImage2 = view.findViewById(R.id.adsResturantImage2);

        resyaurantSectionRecyclerView = view.findViewById(R.id.resyaurantSectionRecyclerView);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        resyaurantSectionRecyclerView.setLayoutManager(manager);

        sectionList = new ArrayList<>();
        mealList = new ArrayList<>();

        sectionAdapter = new RestaurantsSectionAdapter(view.getContext(), sectionList);
        resyaurantSectionRecyclerView.setAdapter(sectionAdapter);
    }

    private void getAds() {
        String url = "https://order.avocodes.com/api/restaurants/" + RestaurantsAdapter.restaurant_id;
        JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject jsonObject = response.getJSONObject("data");
                            JSONArray jsonArray = jsonObject.getJSONArray("ads");
                            JSONObject object = jsonArray.getJSONObject(0);
                            String image_link = object.getString("image");
                            String image = "https://order.avocodes.com/images/" + image_link;

                            Picasso.get().load(image).into(adsResturantImage1);
                            Picasso.get().load(image).into(adsResturantImage2);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        referenceQueue.add(request1);

    }

}