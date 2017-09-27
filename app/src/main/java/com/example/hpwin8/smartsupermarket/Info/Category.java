package com.example.hpwin8.smartsupermarket.Info;

/**
 * Created by taha.amin on 6/21/2017.
 */
import java.util.UUID;

public class Category {
    private String _id;
    private String _categoryName;
    private String _description;

    public Category() {
    }

    public Category(String id,
                   String categoryName,
                   String description)

    {
        this._id = id;
        this._categoryName = categoryName;
        this._description = description;

    }
    public String get_id() {
        return _id;
    }

    public void set_id(String id) {
        this._id = id;
    }

    public String get_categoryName() {
        return _categoryName;
    }

    public void set_categoryName(String categoryName) {
        this._categoryName = categoryName;
    }
    public String get_description() {
        return _description;
    }

    public void set_description(String description) {
        this._description = description;
    }
}
