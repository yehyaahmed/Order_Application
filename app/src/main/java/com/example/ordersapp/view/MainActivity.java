package com.example.ordersapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.ordersapp.R;
import com.example.ordersapp.fragment.CartFragment;
import com.example.ordersapp.fragment.FavoriteFragment;
import com.example.ordersapp.fragment.HomeFragment;
import com.example.ordersapp.fragment.MenuFragment;
import com.example.ordersapp.model.Cart;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    public static List<Cart> cartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        init();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.cart:
                        fragment = new CartFragment();
                        break;
                    case R.id.favorite:
                        fragment = new FavoriteFragment();
                        break;
                    case R.id.menu:
                        fragment = new MenuFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();

                return true;
            }
        });

    }

    private void init() {

        cartList = new ArrayList<>();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
    }

}