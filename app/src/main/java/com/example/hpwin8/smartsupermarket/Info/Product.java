package com.example.hpwin8.smartsupermarket.Info;

import java.net.URL;

/**
 * Created by HP WIN 8 on 6/24/2017.
 */
public class Product {
    private String _id;
    private String _companyName;
    private String _subCategoryName;
    private String _productName;
    private String _description;
    private  String _price;
    private String _availableStock;
    private String _image;
    private String _rate;

    private String _count;

    public Product(String _id,
             String _companyName,
             String _subCategoryName,
             String _productName,
             String _description,
                   String _price,
                   String _availableStock,
                   String _image,
                   String _rate
                   )
    {
        this._id=_id;
        this._companyName= _companyName;
        this._subCategoryName=_subCategoryName;
        this._productName=_productName;
        this._description=_description;
        this._price=_price;
        this._availableStock=_availableStock;
        this._image=_image;
        this._rate=_rate;
        this._count="0";

    }

    public Product()
    {
        this._count="0";
    }


    public String get_count() {
        return _count;
    }

    public void set_count(String _count) {
        this._count = _count;
    }


    public String get_image() {
        return _image;
    }

    public void set_image(String _image) {
        this._image = _image;
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

    public String get_rate() {
        return _rate;
    }

    public void set_rate(String rate) {
        this._rate = rate;
    }


    public String get_availableStock() {
        return _availableStock;
    }

    public void set_availableStock(String _availableStock) {
        this._availableStock = _availableStock;
    }

    public String get_price() {
        return _price;
    }

    public void set_price(String _price) {
        this._price = _price;
    }


    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }




    public String get_productName() {
        return _productName;
    }

    public void set_productName(String _productName) {
        this._productName = _productName;
    }



    public String get_subCategoryName() {
        return _subCategoryName;
    }

    public void set_subCategoryName(String _subCategoryName) {
        this._subCategoryName = _subCategoryName;
    }





}










