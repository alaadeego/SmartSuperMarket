package com.example.hpwin8.smartsupermarket;

/**
 * Created by taha.amin on 6/21/2017.
 */
import android.content.Context;

import com.loopj.android.http.*;
import org.json.*;

import cz.msebera.android.httpclient.HttpEntity;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;


public class SmartmarketRestClient {
    private static final String BASE_URL = "http://tmgholding-001-site20.dtempurl.com/api/MobileAPI/";
   // private static final String BASE_URL = "http://192.168.1.10/smartmarket/api/MobileAPI/";
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.addHeader("Content-Type","application/json");
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }
    public static void postWithBody(Context context, String url, Header[] headers, HttpEntity entity,
                                   ResponseHandlerInterface responseHandler) {
        client.post(context,getAbsoluteUrl(url), headers,entity,"application/json", responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
