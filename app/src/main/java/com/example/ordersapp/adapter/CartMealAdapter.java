package com.example.ordersapp.adapter;

import android.content.Context;
import android.database.Cursor;
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
import com.example.ordersapp.fragment.CartFragment;
import com.example.ordersapp.fragment.MealDetailsToOrderFragment;
import com.example.ordersapp.fragment.RestaurantMealsFragment;
import com.example.ordersapp.model.Cart;
import com.example.ordersapp.model.FavoriteItem;
import com.example.ordersapp.model.Meal;
import com.example.ordersapp.view.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CartMealAdapter extends RecyclerView.Adapter<CartMealAdapter.ViewHolder> {

    private Context context;
    private List<Cart> cartList;

    public CartMealAdapter(Context context, List<Cart> cartList) {
        this.context = context;
        this.cartList = cartList;
    }

    public void setRestaurantsModelList(List<Cart> cartList) {
        this.cartList = cartList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Cart cart = cartList.get(position);

        Picasso.get().load(cart.getImage()).into(holder.cartMealImage);

        holder.cartMealNameTV.setText(cart.getName());
        holder.cartMealPrice.setText(cart.getCartprice());
        holder.cartMealNumberTV.setText(cart.getCartNumber());

        holder.cartMealRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cartList.remove(position);
                notifyDataSetChanged();

                ((FragmentActivity) context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new CartFragment()).commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        if (this.cartList != null) {
            return cartList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView cartMealImage, cartMealRemove;
        TextView cartMealNameTV, cartMealPrice, cartMealNumberTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cartMealImage = itemView.findViewById(R.id.cartMealImage);
            cartMealRemove = itemView.findViewById(R.id.cartMealRemove);

            cartMealNameTV = itemView.findViewById(R.id.cartMealNameTV);
            cartMealPrice = itemView.findViewById(R.id.cartMealPrice);
            cartMealNumberTV = itemView.findViewById(R.id.cartMealNumberTV);

        }
    }
}
