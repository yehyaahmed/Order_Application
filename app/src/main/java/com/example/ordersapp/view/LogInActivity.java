package com.example.ordersapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ordersapp.R;

public class LogInActivity extends AppCompatActivity {

    EditText phoneLoginEt, passwordLoginET;
    TextView loginBtn, forgetPasswordTV, createAccountTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        init();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        String first1 = "ليس لديك حساب؟ ";
        String next1 = "<font color='#000000'><b>أنشئ حساب </b></font>";
        createAccountTV.setText(Html.fromHtml(first1 + next1));
        createAccountTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        forgetPasswordTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LogInActivity.this, PaymentActivity.class);
                startActivity(intent);
            }
        });
    }

    private void init() {

        phoneLoginEt = findViewById(R.id.phoneLoginEt);
        passwordLoginET = findViewById(R.id.passwordLoginET);

        loginBtn = findViewById(R.id.loginBtn);
        forgetPasswordTV = findViewById(R.id.forgetPasswordTV);
        createAccountTV = findViewById(R.id.createAccountTV);


    }
}