package com.example.hpwin8.smartsupermarket;

import android.graphics.Bitmap;

/**
 * Created by HP WIN 8 on 6/18/2017.
 */
public class product {
    String name;
    String price;
    Bitmap image;

  public product(String name, String price ,Bitmap image)
    {
        super();
        this.name=name;
        this.price=price;
        this.image=image;
    }
    public Bitmap getImage() {
        return image;
    }
    public void setImage(Bitmap image) {
        this.image = image;
    }
    public String getName() {
        return name;
    }
    public void setName(String title) {
        this.name = name;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String title) {
        this.price = price;
    }

}
