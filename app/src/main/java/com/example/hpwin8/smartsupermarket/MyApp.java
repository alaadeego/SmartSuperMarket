package com.example.hpwin8.smartsupermarket;

import android.app.Application;

import com.example.hpwin8.smartsupermarket.Info.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP WIN 8 on 6/29/2017.
 */

public class MyApp extends Application {
    private String mUserId;
    private String mUserName;
    private List<Product> mlstBuyerProducts=new ArrayList<Product>();

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }


    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }



    public void addToCart(Product product)
    {
       int pos = mlstBuyerProducts.indexOf(product);
        int count = 1;
        if(pos>-1)
        {
            product =  mlstBuyerProducts.get(pos);
            count = Integer.parseInt(product.get_count()) + 1;
            product.set_count(Integer.toString(count));
        }
        else {
            product.set_count(Integer.toString(count));
            mlstBuyerProducts.add(product);
        }
    }
    public void removeFromCart(Product product)
    {
        int pos = mlstBuyerProducts.indexOf(product);
        int count = 1;
        if(pos>-1)
        {
            product =  mlstBuyerProducts.get(pos);
            count = Integer.parseInt(product.get_count());
            if(count == 1){
                mlstBuyerProducts.remove(pos);
            }
            else {
                product.set_count(Integer.toString(count - 1));
            }
        }

    }

    public List<Product> getCart()
    {
        return mlstBuyerProducts;
    }

    public void emptyCart()
    {
         mlstBuyerProducts = new ArrayList<Product>();
    }


}
