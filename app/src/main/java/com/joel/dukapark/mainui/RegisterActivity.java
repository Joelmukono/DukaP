package com.joel.dukapark.mainui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.joel.dukapark.R;
import com.joel.dukapark.models.RegisterModel.RegisterMdl;
import com.joel.dukapark.models.TokenModel;
import com.joel.dukapark.network.RetrofitClient;
import com.joel.dukapark.network.ServiceInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    Retrofit mRetrofitClient = new RetrofitClient().getRetrofit();

    ServiceInterface mServiceInterface = mRetrofitClient.create(ServiceInterface.class);

    EditText username,email,password,confirmPassword;
    Button signUp;
    TextView loginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        SharedPreferences sharedPreferences = getSharedPreferences("SHOP", Context.MODE_PRIVATE);
        if(sharedPreferences.contains("Token")){
            Intent i = new Intent(RegisterActivity.this,MainActivity.class);
            startActivity(i);
            finish();
            return;

        }

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password1);
        confirmPassword = findViewById(R.id.password2);
        loginText = findViewById(R.id.textViewLogin);
        signUp = findViewById(R.id.sign_up);

        signUp.setOnClickListener(this);

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });




    }

    private void userSignUp(){
        String mUsername = username.getText().toString().trim();
        String mEmail = email.getText().toString().trim();
        String mPass1 = password.getText().toString().trim();
        String mPass2 = confirmPassword.getText().toString().trim();

        if(mUsername.isEmpty()){
            username.setError("Username is Required.");
            username.requestFocus();
        }
        if(mEmail.isEmpty()){
            email.setError("Email is required.");
            email.requestFocus();
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(mEmail).matches()){
            email.setError("A valid email is required.");
            email.requestFocus();
        }

        if(mPass1.isEmpty()){
            password.setError("Password is required.");
            password.requestFocus();
        }

        if(mPass1.length()<8){
            password.setError("Password must be 8 characters long.");
            password.requestFocus();
        }

        if(mPass2.length()<8){
            confirmPassword.setError("Password must be 8 characters long.");
            confirmPassword.requestFocus();
        }

        if(mPass2.isEmpty()){
            confirmPassword.setError("Confirmation password is required.");
            confirmPassword.requestFocus();
        }
        if(!mPass1.equals(mPass2)){
            confirmPassword.setError("Passwords don't match.");
            confirmPassword.requestFocus();
        }

        RegisterMdl registerMdl = new RegisterMdl(mUsername,mEmail,mPass1,mPass2);
        Call<TokenModel> call = mServiceInterface.register(registerMdl);

        call.enqueue(new Callback<TokenModel>() {
            @Override
            public void onResponse(Call<TokenModel> call, Response<TokenModel> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this,"Registration Successful",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(i);
                    finish();



                }else {
                    Toast.makeText(RegisterActivity.this,"error, Registration not successful",Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<TokenModel> call, Throwable t) {
                Toast.makeText(RegisterActivity.this,"error",Toast.LENGTH_LONG).show();

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sign_up:
                userSignUp();
                break;

    }
    }
}