package com.example.hpwin8.smartsupermarket.Info;


/**
 * Created by taha.amin on 6/21/2017.
 */

public class Company {
    private String _id;
    private String _companyName;
    private String _imageUrl;
    private String _rate;
    public Company() {
    }

    public Company(String id,
                   String companyName,
                   String imageUrl,
                   String rate)

    {
        this._id = id;
        this._companyName = companyName;
        this._imageUrl = imageUrl;
        this._rate = rate;
    }
    public String get_id() {
        return _id;
    }

    public void set_id(String id) {
        this._id = id;
    }

    public String get_companyName() {
        return _companyName;
    }

    public void set_companyName(String companyName) {
        this._companyName = companyName;
    }
    public String get_imageUrl() {
        return _imageUrl;
    }

    public void set_imageUrl(String imageUrl) {
        this._imageUrl = imageUrl;
    }
    public String get_rate() {
        return _rate;
    }

    public void set_rate(String rate) {
        this._rate = rate;
    }
}
