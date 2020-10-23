package com.joel.dukapark.uifragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.joel.dukapark.R;
import com.joel.dukapark.models.ProfileModel;
import com.joel.dukapark.network.RetrofitClient;
import com.joel.dukapark.network.ServiceInterface;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProfileFragment extends Fragment {

    public ProfileFragment(){

    }
    ImageView profilePicture;


    Retrofit mRetrofitClient = new RetrofitClient().getRetrofit();

    ServiceInterface mServiceInterface  = mRetrofitClient.create(ServiceInterface.class);

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.profile,
                container, false);

       profilePicture  = rootView.findViewById(R.id.profileImage);




        return rootView;
    }


    public void getProfile(){
        SharedPreferences preferences = this.getActivity().getSharedPreferences("SHOP", Context.MODE_PRIVATE);
        String tokensd ="Token " + preferences.getString("Token","");
        Log.e("token",tokensd);

        Call<List<ProfileModel>> call = mServiceInterface.getAllProfiles(tokensd);
        call.enqueue(new Callback<List<ProfileModel>>() {
            @Override
            public void onResponse(Call<List<ProfileModel>> call, Response<List<ProfileModel>> response) {
                List<ProfileModel> profilePic = response.body();

                profilePic.get(getId()).getProfile_pic();
                Picasso.


            }

            @Override
            public void onFailure(Call<List<ProfileModel>> call, Throwable t) {

            }
        });




    }

}
