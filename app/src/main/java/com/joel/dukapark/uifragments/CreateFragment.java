package com.joel.dukapark.uifragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.snackbar.Snackbar;
import com.joel.dukapark.R;
import com.joel.dukapark.mainui.MainActivity;
import com.joel.dukapark.models.ShopModel;
import com.joel.dukapark.network.RetrofitClient;
import com.joel.dukapark.network.ServiceInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CreateFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "CREATEFRAGMENT";

    Retrofit mRetrofitClient = new RetrofitClient().getRetrofit();

    ServiceInterface mMessageService  = mRetrofitClient.create(ServiceInterface.class);

    public CreateFragment(){

    }

    EditText mShopName,mShopCategory,mShopTel,mShopCounty,mShopStreet,mSaleScale;
    Button mCreateShop;



    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.add_shops,container,false);

        mShopName = rootView.findViewById(R.id.shopNameEditText);
        mShopTel = rootView.findViewById(R.id.shopTelEditText);
        mShopCategory = rootView.findViewById(R.id.shopCategoryEditText);
        mShopCounty = rootView.findViewById(R.id.shopCounty);
        mShopStreet = rootView.findViewById(R.id.shopStreet);
        mSaleScale = rootView.findViewById(R.id.saleScale);

        mCreateShop = rootView.findViewById(R.id.createShopButton);


        mCreateShop.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.createShopButton:
                createShop();
                break;
        }
    }


    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    private void createShop() {
        String shopname = mShopName.getText().toString().trim();
        String shopTele = mShopTel.getText().toString().trim();
        String shopCat = mShopCategory.getText().toString().trim();
        String shopCounty = mShopCounty.getText().toString().trim();
        String shopStreet = mShopCounty.getText().toString().trim();
        String saleScale = mSaleScale.getText().toString().trim();


        SharedPreferences preferences = this.getActivity().getSharedPreferences("SHOP", Context.MODE_PRIVATE);
        String tokens ="Token " + preferences.getString("Token",null);
        Log.e("token",tokens);

        SharedPreferences preferences1 = this.getActivity().getSharedPreferences("ID",Context.MODE_PRIVATE);
        int ids = preferences1.getInt("Id",0);
        Log.e("IDs", String.format("value = %d", ids));


        Call<ShopModel> call = mMessageService.createShop(tokens,ids,shopname,shopTele,shopCat,shopCounty,shopStreet,saleScale);
        call.enqueue(new Callback<ShopModel>() {
            @Override
            public void onResponse(Call<ShopModel> call, Response<ShopModel> response) {
                if(response.isSuccessful()){
                    Fragment fragment = null;
                    fragment = new HomeFragment();
                    replaceFragment(fragment);

                    View v = getActivity().findViewById(android.R.id.content);

                    Snackbar.make(v,"A shop has been created!",Snackbar.LENGTH_LONG).show();

                }
                else{
                    View v = getActivity().findViewById(android.R.id.content);
                    Snackbar.make(v,"A shop has been created!",Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ShopModel> call, Throwable t) {
                Toast.makeText(getActivity(),"ERROR",Toast.LENGTH_LONG).show();


            }
        });
    }
}

