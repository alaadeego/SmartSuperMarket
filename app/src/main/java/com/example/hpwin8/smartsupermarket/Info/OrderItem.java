package com.example.hpwin8.smartsupermarket.Info;

import com.google.gson.GsonBuilder;

/**
 * Created by HP WIN 8 on 7/1/2017.
 */

public class OrderItem {
    public  String orderId;
    public String productId;
    public Integer count;
    public double pricePerItem;
    public String id;

    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, OrderItem.class);
    }
}
