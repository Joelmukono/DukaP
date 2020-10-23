package com.joel.dukapark.uifragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.joel.dukapark.R;
import com.joel.dukapark.adapters.ShopAdapter;
import com.joel.dukapark.models.ShopModel;
import com.joel.dukapark.network.RetrofitClient;
import com.joel.dukapark.network.ServiceInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment implements View.OnClickListener {
    public HomeFragment() {
        // Required empty public constructor
    }
    Retrofit mRetrofitClient = new RetrofitClient().getRetrofit();

    ServiceInterface mMessageService  = mRetrofitClient.create(ServiceInterface.class);

    RecyclerView mRecyclerView;
    ShopAdapter mShopListAdapter = new ShopAdapter();


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home,container,false);
        mRecyclerView = rootView.findViewById(R.id.recyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        getAllShops();

        return rootView;
    }

    public void getAllShops(){
        SharedPreferences preferences = this.getActivity().getSharedPreferences("SHOP", Context.MODE_PRIVATE);
        String tokensd ="Token " + preferences.getString("Token","");
        Log.e("token",tokensd);

        Call<List<ShopModel>> call = mMessageService.getAllShops(tokensd);
        call.enqueue(new Callback<List<ShopModel>>() {
            @Override
            public void onResponse(Call<List<ShopModel>> call, Response<List<ShopModel>> response) {
                if(response.isSuccessful()){


                    List<ShopModel> shopModelList = response.body();

                    mShopListAdapter.setData(shopModelList);
                    mRecyclerView.setAdapter(mShopListAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<ShopModel>> call, Throwable t) {

                Log.e("error","error");

            }
        });
    }



    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        switch (v.getId()){

        }

    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
