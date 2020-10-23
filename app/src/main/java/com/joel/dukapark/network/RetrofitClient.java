package com.joel.dukapark.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private OkHttpClient getInterceptor(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        return client;
    }

    Retrofit.Builder mBuilder = new Retrofit.Builder()
            .baseUrl("http://api.newbementors.com/api/v1/")
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create());

    private Retrofit mRetrofit = mBuilder.build();

    public Retrofit getRetrofit() {
        return mRetrofit;
    }
}
