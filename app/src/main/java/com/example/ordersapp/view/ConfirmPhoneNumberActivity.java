package com.example.ordersapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ordersapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class ConfirmPhoneNumberActivity extends AppCompatActivity {

    EditText boxNumber1, boxNumber2,
            boxNumber3, boxNumber4;

    TextView confirmPhoneNumberBtnTV, sendAgainTv;

    String phone;

    String verificationId;

    ProgressDialog progressDialog;

    PhoneAuthProvider.ForceResendingToken forceResendingToken;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;

    static final String TAG = "MAIN_TAG";

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_phone_number);

        init();

        firebaseAuth = FirebaseAuth.getInstance();

        phone = getIntent().getStringExtra("phone");

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);


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

                verificationId = vId;
                forceResendingToken = token;
                progressDialog.dismiss();
            }
        };


        sendAgainTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                resendVerificationCode(phone, forceResendingToken);
            }
        });

        confirmPhoneNumberBtnTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*
                String number1 = boxNumber1.getText().toString().trim();
                String number2 = boxNumber2.getText().toString().trim();
                String number3 = boxNumber3.getText().toString().trim();
                String number4 = boxNumber4.getText().toString().trim();

                if (!number1.isEmpty() && number1 != null &&
                        !number2.isEmpty() && number2 != null &&
                        !number3.isEmpty() && number3 != null &&
                        !number4.isEmpty() && number4 != null) {

                    String code = number1 + number2 + number3 + number4;
                    verifyPhoneNumberWithCode(verificationId, code);


                }

 */

                Intent intent = new Intent(ConfirmPhoneNumberActivity.this, ConfirmPhoneNumberActivity.class);
                startActivity(intent);

            }
        });

    }

    private void verifyPhoneNumberWithCode(String verificationId, String code) {

        progressDialog.setMessage("التحقق من الكود");
        progressDialog.show();

        try {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
            signInWithPhoneAuthCredential(credential);
        } catch (Exception e) {
        }
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {

        firebaseAuth.signInWithCredential(credential)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        progressDialog.dismiss();

                        Intent intent = new Intent(ConfirmPhoneNumberActivity.this, LogInActivity.class);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(ConfirmPhoneNumberActivity.this, "حاول مرة أخرى", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void resendVerificationCode(String phone, PhoneAuthProvider.ForceResendingToken token) {
        progressDialog.setMessage("إعادة إرسال الكود");
        progressDialog.show();

        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(firebaseAuth)
                .setPhoneNumber(phone)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(callbacks)
                .setForceResendingToken(token)
                .build();

        PhoneAuthProvider.verifyPhoneNumber(options);

    }

    private void init() {

        boxNumber1 = findViewById(R.id.boxNumber1);
        boxNumber2 = findViewById(R.id.boxNumber2);
        boxNumber3 = findViewById(R.id.boxNumber3);
        boxNumber4 = findViewById(R.id.boxNumber4);

        confirmPhoneNumberBtnTV = findViewById(R.id.confirmPhoneNumberBtnTV);

        sendAgainTv = findViewById(R.id.sendAgainTv);
    }
}