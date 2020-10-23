package com.joel.dukapark.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joel.dukapark.R;
import com.joel.dukapark.models.ShopModel;
import com.squareup.picasso.Picasso;


import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopViewHolder> {

    private List<ShopModel> shopsList;
    private Context mContext;

    public ShopAdapter() {
    }

    public void setData(List<ShopModel> shopsList) {
        this.shopsList = shopsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ShopAdapter.ShopViewHolder(LayoutInflater.from(mContext).inflate(R.layout.shop_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ShopViewHolder holder, int position) {

        ShopModel shopModel = shopsList.get(position);
        String ShopName = shopModel.getShopname();
        String ShopTel = shopModel.getShoptel();
        String ShopCat = shopModel.getShopcategory();
        String shopStreet = shopModel.getShopstreet();
        String saleScale = shopModel.getSalescale();
        String shopcounty = shopModel.getShopcounty();
        String shopImage = shopModel.getShopphoto();


        holder.shopcategory.setText(ShopCat);
        holder.shopName.setText(ShopName);
        holder.shoptel.setText(ShopTel);
        holder.shopstreet.setText(shopStreet);
        holder.salescale.setText(saleScale);
        holder.shopcounty.setText(shopcounty);
        Picasso.get().load(shopImage).into(holder.mShopImageView);

    }

    @Override
    public int getItemCount() {
        return shopsList.size();
    }

    public class ShopViewHolder extends RecyclerView.ViewHolder {
        TextView shopName,shoptel,shopcategory,shopstreet,shopcounty,salescale;
        ImageView mShopImageView;

        public ShopViewHolder(@NonNull View itemView) {
            super(itemView);
            shopName = itemView.findViewById(R.id.shopNameTextView);
            shopstreet = itemView.findViewById(R.id.shopStreet);
            shopcategory = itemView.findViewById(R.id.shopCategoryTextView);
            shopcounty = itemView.findViewById(R.id.shopCounty);
            salescale = itemView.findViewById(R.id.saleScale);
            shoptel = itemView.findViewById(R.id.shopTelText);
            mShopImageView = itemView.findViewById(R.id.shopImageView);
        }
    }
}
