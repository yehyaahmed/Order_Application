package com.example.ordersapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ordersapp.R;
import com.example.ordersapp.adapter.CartMealAdapter;
import com.example.ordersapp.model.Cart;
import com.example.ordersapp.view.MainActivity;

public class CartFragment extends Fragment {

    RecyclerView cartRecyclerView;
    CartMealAdapter cartMealAdapter;

    TextView cartFinalPriceToMealsTV, cartallPriceToMealsTV;

    Button confirmCartMealBtnTV;

    int price = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        init(view);

        for (Cart c : MainActivity.cartList) {
            price += Integer.parseInt(c.getCartprice());
        }

        cartallPriceToMealsTV.setText(price + "");

        int finalPrice = price + 10;
        cartFinalPriceToMealsTV.setText(finalPrice + "");
        return view;
    }

    private void init(View view) {

        confirmCartMealBtnTV = view.findViewById(R.id.confirmCartMealBtnTV);
        cartFinalPriceToMealsTV = view.findViewById(R.id.cartFinalPriceToMealsTV);
        cartallPriceToMealsTV = view.findViewById(R.id.cartallPriceToMealsTV);

        cartRecyclerView = view.findViewById(R.id.cartRecyclerView);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        cartRecyclerView.setLayoutManager(manager);

        cartMealAdapter = new CartMealAdapter(view.getContext(), MainActivity.cartList);
        cartRecyclerView.setAdapter(cartMealAdapter);
        cartMealAdapter.notifyDataSetChanged();

    }
}