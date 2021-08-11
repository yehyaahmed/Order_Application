package com.example.ordersapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ordersapp.R;
import com.example.ordersapp.fragment.RestaurantSectionFragment;
import com.example.ordersapp.model.Restaurants;
import com.example.ordersapp.model.RestaurantsModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.ViewHolder> {

    private Context context;
    private List<Restaurants> restaurantsModelList;
    public static int restaurant_id;
    public static String restaurant_name;

    public RestaurantsAdapter(Context context, List<Restaurants> restaurantsModelList) {
        this.context = context;
        this.restaurantsModelList = restaurantsModelList;
    }

    public void setRestaurantsModelList(List<Restaurants> restaurantsModelList) {
        this.restaurantsModelList = restaurantsModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.all_restaurants_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Restaurants restaurants = restaurantsModelList.get(position);

        holder.restaurantsName.setText(restaurants.getName());
        Picasso.get().load(restaurants.getImage_link()).into(holder.restaurantsImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("test", restaurants.getId() + "");
                restaurant_id = restaurants.getId();
                restaurant_name = restaurants.getName();

                ((FragmentActivity) context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new RestaurantSectionFragment()).commit();

            }
        });

    }

    @Override
    public int getItemCount() {
        if (this.restaurantsModelList != null) {
            return restaurantsModelList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView restaurantsImage;
        TextView restaurantsName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            restaurantsImage = itemView.findViewById(R.id.restaurantsImage);
            restaurantsName = itemView.findViewById(R.id.restaurantsName);

        }
    }
}
