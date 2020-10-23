package com.joel.dukapark.mainui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.joel.dukapark.R;
import com.joel.dukapark.models.TokenModel;
import com.joel.dukapark.models.loginModel.LoginModel;
import com.joel.dukapark.network.RetrofitClient;
import com.joel.dukapark.network.ServiceInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    TextView mTextViewRegister;
    EditText mUsername, mPassword;
    Button mLoginButton;

    Retrofit mRetrofit = new RetrofitClient().getRetrofit();

    ServiceInterface mServiceInterface = mRetrofit.create(ServiceInterface.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mTextViewRegister = findViewById(R.id.textViewRegister);
        mUsername = findViewById(R.id.emailusername);
        mPassword =  findViewById(R.id.loginpassword);
        mLoginButton = findViewById(R.id.login);

        mLoginButton.setOnClickListener(this);

        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                login();
                break;
        }

    }

    private void login(){
        String username = mUsername.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        if(username.isEmpty()){
            mUsername.setError("Username is Required.");
            mUsername.requestFocus();
        }

        if(password.isEmpty()){
            mPassword.setError("Email is required");
            mPassword.requestFocus();
        }

        LoginModel loginModel = new LoginModel(username,password);
        Call<TokenModel> tokenModelCall = mServiceInterface.login(loginModel);
        tokenModelCall.enqueue(new Callback<TokenModel>() {
            @Override
            public void onResponse(Call<TokenModel> call, Response<TokenModel> response) {
                if (response.isSuccessful()) {
                    Log.e("Login","Unresponse invoked");

                    TokenModel tokenModel = response.body();
                    String token = tokenModel.getToken();
                    Log.e("Login token",token);

                    SharedPreferences sharedPreferences = getSharedPreferences("SHOP", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Token",token);

                    editor.apply();

                    TokenModel userId = response.body();
                    int id = userId.getUser().getId();

                    SharedPreferences sharedPreferences1 = getSharedPreferences("ID",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = sharedPreferences1.edit();
                    editor1.putInt("Id",id);
                    editor1.apply();









                    Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();




                }else {
                    Toast.makeText(LoginActivity.this,"error, login not successful",Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<TokenModel> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"error",Toast.LENGTH_LONG).show();
            }
        });


    }

    }
