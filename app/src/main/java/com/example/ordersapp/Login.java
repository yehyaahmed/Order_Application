package com.example.ordersapp;


import com.example.ordersapp.model.LoginResponse;
import com.example.ordersapp.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login {
    public static boolean getStatus = false;

    public static void logIn(String email, String password) {

        Call<LoginResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .userLogin(email, password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                if (loginResponse.isStatus()) {
                    getStatus = true;
                } else {
                    getStatus = false;
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                getStatus = false;
            }
        });

    }
}
