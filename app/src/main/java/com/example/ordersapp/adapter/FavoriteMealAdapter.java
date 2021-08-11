package com.example.ordersapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ordersapp.DBHelper;
import com.example.ordersapp.R;
import com.example.ordersapp.fragment.MealDetailsToOrderFragment;
import com.example.ordersapp.model.Meal;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriteMealAdapter extends RecyclerView.Adapter<FavoriteMealAdapter.ViewHolder> {

    private Context context;
    private List<Meal> mealList;

    public FavoriteMealAdapter(Context context, List<Meal> mealList) {
        this.context = context;
        this.mealList = mealList;
    }

    public void setRestaurantsModelList(List<Meal> mealList) {
        this.mealList = mealList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.favorite_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Meal meal = mealList.get(position);
        String image = "https://order.avocodes.com/images/" + meal.getImage();

        holder.favoriteMealNameTV.setText(meal.getName());
        Picasso.get().load(image).into(holder.favoriteMealImage);

        holder.favoriteMealResturantPriceTV.setText(meal.getPrice() + "");

        holder.favoriteMealRemoveIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // remove Item
                DBHelper dbHelper = new DBHelper(context);
                dbHelper.removeMeal(meal.getId());
                mealList.remove(position);
                notifyDataSetChanged();

            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((FragmentActivity) context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new MealDetailsToOrderFragment()).commit();

            }
        });

    }

    @Override
    public int getItemCount() {
        if (this.mealList != null) {
            return mealList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView favoriteMealImage, favoriteMealRemoveIV;
        TextView favoriteMealResturantName, favoriteMealResturantPriceTV, favoriteMealNameTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            favoriteMealImage = itemView.findViewById(R.id.favoriteMealImage);
            favoriteMealRemoveIV = itemView.findViewById(R.id.favoriteMealRemoveIV);

            favoriteMealResturantName = itemView.findViewById(R.id.favoriteMealResturantName);
            favoriteMealResturantPriceTV = itemView.findViewById(R.id.favoriteMealResturantPriceTV);
            favoriteMealNameTV = itemView.findViewById(R.id.favoriteMealNameTV);

        }
    }
}
