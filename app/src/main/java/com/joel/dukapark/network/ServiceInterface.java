package com.joel.dukapark.network;

import com.joel.dukapark.models.ProfileModel;
import com.joel.dukapark.models.RegisterModel.RegisterMdl;
import com.joel.dukapark.models.ShopModel;
import com.joel.dukapark.models.TokenModel;
import com.joel.dukapark.models.loginModel.LoginModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ServiceInterface {


    @POST("rest-auth/registration/")
    Call<TokenModel> register(@Body RegisterMdl registerMdl);

    @POST("users/auth/")
    Call<TokenModel> login(@Body LoginModel loginModel);

    @FormUrlEncoded
    @POST("shopslist/shops/")
    Call<ShopModel> createShop(@Header("Authorization") String token, @Field("creator") int id, @Field("shopname") String shopname,
                               @Field("shoptel") String shoptel, @Field("shopcategory")String shopcategory,
                               @Field("shopcounty")String shopcounty,@Field("shopstreet")String shopstreet,
                               @Field("salescale")String salescale);

    @GET("shopslist/shops/")
    Call<List<ShopModel>> getAllShops(@Header("Authorization") String token);

    @GET("profile/profile/")
    Call<List<ProfileModel>> getAllProfiles(@Header("Authorization") String token);


}
