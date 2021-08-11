package com.example.ordersapp.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ordersapp.R;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SignUpActivity extends AppCompatActivity {

    EditText phoneLoginEt;
    TextView createAccountBtnTV;

    PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;

    FirebaseAuth firebaseAuth;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        init();

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);

        createAccountBtnTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = phoneLoginEt.getText().toString().trim();
                if (!phone.isEmpty() && phone != null) {

                    startPhoneNumberVerification(phone);

                    Intent intent = new Intent(SignUpActivity.this, ConfirmPhoneNumberActivity.class);
                    intent.putExtra("phone", phone);
                    startActivity(intent);
                }
            }
        });

        callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                progressDialog.dismiss();
            }

            @Override
            public void onCodeSent(@NonNull String vId, @NonNull PhoneAuthProvider.ForceResendingToken token) {
                super.onCodeSent(vId, token);
                progressDialog.dismiss();
            }
        };

    }

    private void startPhoneNumberVerification(String phone) {

        progressDialog.setMessage("التحقق من رقم الهاتف");
        progressDialog.show();

        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(firebaseAuth)
                .setPhoneNumber(phone)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(callbacks)
                .build();

        PhoneAuthProvider.verifyPhoneNumber(options);

    }

    private void init() {

        phoneLoginEt = findViewById(R.id.phoneLoginEt);
        createAccountBtnTV = findViewById(R.id.createAccountBtnTV);
    }

}
