package com.example.ordersapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ordersapp.R;
import com.example.ordersapp.adapter.RestaurantsAdapter;
import com.example.ordersapp.adapter.RestaurantsMealAdapter;
import com.example.ordersapp.model.Cart;
import com.example.ordersapp.model.Meal;
import com.example.ordersapp.view.MainActivity;
import com.example.ordersapp.viewmodel.RestaurantMealViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MealDetailsToOrderFragment extends Fragment {

    private List<Meal> mealList;
    private RestaurantMealViewModel viewModel;

    ImageView mealImage2IVFragment, mealImage1IVFragment;

    TextView mealDescriptionTVFragment, mealAllSalareTVFragment, mealNumberTVFragment,
            mealNameTVFragment, resturantNameInMealFragmentTV, mealPriceTVFragment,
            mealAddToCartTVFragment, addButtonTVFragment, lossButtonTVFragment;

    int number = 0;

    String image, mealName, mealPrice;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_meal_details_to_order, container, false);

        init(view);

        viewModel = ViewModelProviders.of(this).get(RestaurantMealViewModel.class);
        viewModel.getRestaurantsList0bserver().observe((LifecycleOwner) view.getContext(), new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                mealList = meals;
                setData();
            }
        });

        viewModel.makeApiCall();

        mealAddToCartTVFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (number > 0) {
                    MainActivity.cartList.add(new Cart(image, mealName, mealAllSalareTVFragment.getText().toString(), number + ""));
                    getActivity().getSupportFragmentManager().beginTransaction().
                            replace(R.id.fragment_container, new HomeFragment()).commit();

                }
            }
        });

        return view;
    }

    private void init(View view) {

        mealImage1IVFragment = view.findViewById(R.id.mealImage1IVFragment);
        mealImage2IVFragment = view.findViewById(R.id.mealImage2IVFragment);

        mealDescriptionTVFragment = view.findViewById(R.id.mealDescriptionTVFragment);
        mealAllSalareTVFragment = view.findViewById(R.id.mealAllSalareTVFragment);
        mealNumberTVFragment = view.findViewById(R.id.mealNumberTVFragment);
        mealNameTVFragment = view.findViewById(R.id.mealNameTVFragment);
        resturantNameInMealFragmentTV = view.findViewById(R.id.resturantNameInMealFragmentTV);
        mealPriceTVFragment = view.findViewById(R.id.mealPriceTVFragment);
        mealAddToCartTVFragment = view.findViewById(R.id.mealAddToCartTVFragment);
        addButtonTVFragment = view.findViewById(R.id.addButtonTVFragment);
        lossButtonTVFragment = view.findViewById(R.id.lossButtonTVFragment);

    }

    private void setData() {
        int mealAllSalareTVResult = Integer.parseInt(mealList.get(0).getPrice()) * number;

        mealAllSalareTVFragment.setText(mealAllSalareTVResult + "");

        mealNumberTVFragment.setText(number + "");

        addButtonTVFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number++;
                mealNumberTVFragment.setText(number + "");
                int mealAllSalareTVResult = Integer.parseInt(mealList.get(0).getPrice()) * number;
                mealAllSalareTVFragment.setText(mealAllSalareTVResult + "");

            }
        });

        lossButtonTVFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (number > 0) {
                    number--;
                    mealNumberTVFragment.setText(number + "");

                    int mealAllSalareTVResult = Integer.parseInt(mealList.get(0).getPrice()) * number;
                    mealAllSalareTVFragment.setText(mealAllSalareTVResult + "");

                }
            }
        });

        mealDescriptionTVFragment.setText(mealList.get(0).getDescription() + "");
        mealNameTVFragment.setText(mealList.get(0).getName() + "");
        mealPriceTVFragment.setText(mealList.get(0).getPrice() + "");

        resturantNameInMealFragmentTV.setText(RestaurantsAdapter.restaurant_name + "");

        mealName = mealList.get(0).getName();
        mealPrice = mealList.get(0).getPrice();

        image = "https://order.avocodes.com/images/" + mealList.get(0).getImage();

        Picasso.get().load(image).into(mealImage1IVFragment);
        Picasso.get().load(image).into(mealImage2IVFragment);

    }
}