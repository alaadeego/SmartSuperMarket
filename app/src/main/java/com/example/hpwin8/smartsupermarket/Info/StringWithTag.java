package com.example.hpwin8.smartsupermarket.Info;

/**
 * Created by taha.amin on 6/21/2017.
 */

public class StringWithTag {
    public String string;
    public String tag;

    public StringWithTag(String stringPart, String tagPart) {
        string = stringPart;
        tag = tagPart;
    }
    public String value() {
        return tag;
    }
    @Override
    public String toString() {
        return string;
    }
}
