package com.joel.dukapark.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShopModel {
    @SerializedName("id")
    private int id;
    @SerializedName("creator")
    private int creator;
    @SerializedName("shopname")
    private String shopname;
    @SerializedName("shopphoto")
    private String shopphoto;
    @SerializedName("shoptel")
    private String shoptel;
    @SerializedName("shopcategory")
    private String shopcategory;
    @SerializedName("shopcounty")
    private String shopcounty;
    @SerializedName("shopstreet")
    private String shopstreet;
    @SerializedName("salescale")
    private String salescale;


    public ShopModel(int id, int creator, String shopname, String shopphoto,
                     String shoptel, String shopcategory, String shopcounty,
                     String shopstreet, String salescale) {
        this.id = id;
        this.creator = creator;
        this.shopname = shopname;
        this.shopphoto = shopphoto;
        this.shoptel = shoptel;
        this.shopcategory = shopcategory;
        this.shopcounty = shopcounty;
        this.shopstreet = shopstreet;
        this.salescale = salescale;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getShopphoto() {
        return shopphoto;
    }

    public void setShopphoto(String shopphoto) {
        this.shopphoto = shopphoto;
    }

    public String getShoptel() {
        return shoptel;
    }

    public void setShoptel(String shoptel) {
        this.shoptel = shoptel;
    }

    public String getShopcategory() {
        return shopcategory;
    }

    public void setShopcategory(String shopcategory) {
        this.shopcategory = shopcategory;
    }

    public String getShopcounty() {
        return shopcounty;
    }

    public void setShopcounty(String shopcounty) {
        this.shopcounty = shopcounty;
    }

    public String getShopstreet() {
        return shopstreet;
    }

    public void setShopstreet(String shopstreet) {
        this.shopstreet = shopstreet;
    }

    public String getSalescale() {
        return salescale;
    }

    public void setSalescale(String salescale) {
        this.salescale = salescale;
    }


}
