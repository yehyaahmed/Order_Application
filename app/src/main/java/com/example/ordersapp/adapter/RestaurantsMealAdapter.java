package com.example.ordersapp.adapter;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
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
import com.example.ordersapp.fragment.RestaurantMealsFragment;
import com.example.ordersapp.model.FavoriteItem;
import com.example.ordersapp.model.Meal;
import com.example.ordersapp.model.Section;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsMealAdapter extends RecyclerView.Adapter<RestaurantsMealAdapter.ViewHolder> {

    private Context context;
    private List<Meal> mealList;
    public static int restaurant_meal_id;
    List<FavoriteItem> favoriteItems = new ArrayList<>();

    public RestaurantsMealAdapter(Context context, List<Meal> mealList) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.meal_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        Meal meal = mealList.get(position);
        String image = "https://order.avocodes.com/images/" + meal.getImage();

        holder.mealNameTV.setText(meal.getName());
        Picasso.get().load(image).into(holder.mealImageIV);

        holder.mealPriceTV.setText(meal.getPrice() + "");
        holder.mealCartTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // add to my cart

            }
        });

        getFavMeal(context);

        if (favoriteItems != null && favoriteItems.size() >= 0) {
            for (FavoriteItem f : favoriteItems) {
                if (f.getId() == meal.getId()) {
                    if (f.getIsFavorite().equals("like")) {
                        holder.mealfavoriteIV.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_favorite));
                    } else {
                        holder.mealfavoriteIV.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_favorite_border));
                    }
                } else {
                    holder.mealfavoriteIV.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_favorite_border));
                }
            }
        } else {
            holder.mealfavoriteIV.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_favorite_border));
        }

        holder.mealfavoriteIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHelper dbHelper = new DBHelper(context);
                boolean b = dbHelper.checkMeal(meal.getId());

                if (b) {
                    for (FavoriteItem f : favoriteItems) {
                        if (f.getId() == meal.getId()) {
                            if (f.getIsFavorite().equals("like")) {
                                dbHelper.updateMeal(meal.getId(), "no like");
                                notifyDataSetChanged();
                            } else {
                                dbHelper.updateMeal(meal.getId(), "like");
                                notifyDataSetChanged();
                            }
                        } else {
                            dbHelper.insertData(meal.getId(), "no like");
                            notifyDataSetChanged();
                        }
                    }
                } else {
                    dbHelper.insertData(meal.getId(), "like");
                    notifyDataSetChanged();
                }
                notifyDataSetChanged();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restaurant_meal_id = meal.getId();

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

        ImageView mealImageIV, mealfavoriteIV;
        TextView mealPriceTV, mealCartTV, mealNameTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mealImageIV = itemView.findViewById(R.id.mealImageIV);
            mealfavoriteIV = itemView.findViewById(R.id.mealfavoriteIV);

            mealPriceTV = itemView.findViewById(R.id.mealPriceTV);
            mealCartTV = itemView.findViewById(R.id.mealCartTV);
            mealNameTV = itemView.findViewById(R.id.mealNameTV);

        }
    }


    private void getFavMeal(Context context) {

        DBHelper dbHelper = new DBHelper(context);
        Cursor cursor = dbHelper.getAllMeals();

        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndex("id"));
                    String isFavorite = cursor.getString(cursor.getColumnIndex("isFavorite"));
                    favoriteItems.add(new FavoriteItem(id, isFavorite));
                } while (
                        cursor.moveToNext()
                );
            }
        }
    }
}
