package com.example.ordersapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ordersapp.R;
import com.example.ordersapp.fragment.RestaurantMealsFragment;
import com.example.ordersapp.fragment.RestaurantSectionFragment;
import com.example.ordersapp.model.Restaurants;
import com.example.ordersapp.model.Section;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RestaurantsSectionAdapter extends RecyclerView.Adapter<RestaurantsSectionAdapter.ViewHolder> {

    private Context context;
    private List<Section> sectionList;
    public static int restaurant_section_id;
    public static String restaurant_section_name;

    public RestaurantsSectionAdapter(Context context, List<Section> sectionList) {
        this.context = context;
        this.sectionList = sectionList;
    }

    public void setRestaurantsModelList(List<Section> sectionList) {
        this.sectionList = sectionList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.restaurant_section_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Section section = sectionList.get(position);
        Log.d("testAdapterSection", section.getName() + " ");
        String image = "https://order.avocodes.com/images/" + section.getImage();

        holder.sectionNameTV.setText(section.getName());
        Picasso.get().load(image).into(holder.sectionImageIV);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restaurant_section_id = section.getId();
                restaurant_section_name = section.getName();

                ((FragmentActivity) context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new RestaurantMealsFragment()).commit();

            }
        });

    }

    @Override
    public int getItemCount() {
        if (this.sectionList != null) {
            return sectionList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView sectionImageIV;
        TextView sectionNameTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            sectionImageIV = itemView.findViewById(R.id.sectionImageIV);
            sectionNameTV = itemView.findViewById(R.id.sectionNameTV);

        }
    }
}
