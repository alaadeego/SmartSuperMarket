package com.example.hpwin8.smartsupermarket;

/**
 * Created by taha.amin on 6/21/2017.
 */
import com.google.gson.Gson;
import org.json.*;
import com.loopj.android.http.*;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

import com.example.hpwin8.smartsupermarket.Info.*;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;


public class SmartmarketRestClientUsage {
    public void getCompanies(String companyId, final Context applicationContext, final GridView grid) {
        final List<Company> lstCompanies = new ArrayList<Company>();
        try {
            RequestParams params = new RequestParams();
            params.put("companyId", companyId);

            SmartmarketRestClient.get("GetCompanies", params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    // If the response is JSONObject instead of expected JSONArray
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray parentArray) {
                    try {
                        Gson gson = new Gson();
                        for (int i = 0; i < parentArray.length(); i++) {

                            JSONObject finalObject = parentArray.getJSONObject(i);
                            String _id = finalObject.get("id").toString();
                            String companyName = finalObject.getString("companyName");
                            String rate = finalObject.getString("rate");
                            String imageUrl = finalObject.getString("imageURL");
                            lstCompanies.add(new Company(_id, companyName, imageUrl, rate));


                            // Do something with the response
                            System.out.println(companyName);
                        }
                        if (lstCompanies.size() > 0) {

                            grid.setAdapter(new CompanyAdapter(applicationContext, lstCompanies));
                        }
                    } catch (Exception e) {

                    }

                }
            });

        } catch (Exception e) {

        }

    }


    public void getProducts(String companyId, String categoryId, String search, final Context applicationContext, final GridView grid) {

        final List<Product> lstProducts = new ArrayList<Product>();
        try {
            RequestParams params = new RequestParams();

            params.put("companyId", companyId);
            params.put("categoryId", categoryId);
            params.put("search", search);


            SmartmarketRestClient.get("GetProducts", params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    // If the response is JSONObject instead of expected JSONArray
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray parentArray) {
                    try {
                        Gson gson = new Gson();
                        for (int i = 0; i < parentArray.length(); i++) {

                            JSONObject finalObject = parentArray.getJSONObject(i);

                            String _id = finalObject.get("id").toString();
                            String _rate = finalObject.getString("rate");
                            String _description = finalObject.getString("description");
                            String _productName = finalObject.getString("productName");
                            String _companyName = finalObject.getJSONObject("company").getString("companyName");
                            String _subCategoryName = finalObject.getJSONObject("subCategory").getString("subCategoryName");
                            String _price = finalObject.getString("price");
                            String _availableStock = finalObject.getString("availableStock");
                            String _image = finalObject.getString("imageURL");


                            lstProducts.add(new Product(_id, _companyName, _subCategoryName, _productName, _description, _price, _availableStock, _image, _rate));

                        }

                        //grid=(GridView)findViewById(R.id.gridView);
                        if (lstProducts.size() > 0) {

                            MyApp mApp = ((MyApp)applicationContext.getApplicationContext());
                            ///--------------Sort
                          /*  if(sortFlag.equals("1"))
                            {
                                for(int i=0; i<lstProducts.size()-1;i++)
                                {
                                    for(int j=i+1;j<lstProducts.size();j++)
                                    {
                                        if( Integer.parseInt(lstProducts.get(i).get_price())>Integer.parseInt(lstProducts.get(i).get_price()))
                                        {
                                            Product temp;
                                            temp=lstProducts.get(i);
                                            lstProducts.set(i,lstProducts.get(j));
                                            lstProducts.set(j,temp);

                                        }
                                    }
                                }
                            }*/
                            grid.setAdapter(new ProductAdapter(applicationContext, lstProducts,mApp));
                        }

                    } catch (Exception e) {
                        Log.e("", e.getMessage());
                    }

                }
            });

        } catch (Exception e) {
            Log.e("", e.getMessage());
        }


    }

    public void getCategories(String categoryId, final Context applicationContext, final Spinner gategory_spinner) {

        // gategory_spinner.setOnItemSelectedListener(this);


        final List<Category> lstCategories = new ArrayList<Category>();
        try {
            RequestParams params = new RequestParams();

            params.put("categoryId", categoryId);

            SmartmarketRestClient.get("GetCategories", params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    // If the response is JSONObject instead of expected JSONArray
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray parentArray) {
                    try {
                        Gson gson = new Gson();
                        lstCategories.add(new Category("-1", "All", "All Categories"));
                        for (int i = 0; i < parentArray.length(); i++) {

                            JSONObject finalObject = parentArray.getJSONObject(i);
                            String _id = finalObject.get("id").toString();
                            String categoryName = finalObject.getString("categoryName");
                            String description = finalObject.getString("description");


                            lstCategories.add(new Category(_id, categoryName, description));


                            // Do something with the response
                            System.out.println(categoryName);
                        }
                        List<StringWithTag> list = new ArrayList<StringWithTag>();
                        for (Category item : lstCategories) {
                            list.add(new StringWithTag(item.get_categoryName(),
                                    item.get_id()));
                        }

                        //ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_template,Gategory_list);
                        ArrayAdapter<StringWithTag> dataAdapter = new ArrayAdapter<StringWithTag>
                                (applicationContext, R.layout.spinner_template, list);

                        gategory_spinner.setAdapter(dataAdapter);


                    } catch (Exception e) {
                        Log.e("", e.getMessage());
                    }

                }
            });

        } catch (Exception e) {
            Log.e("", e.getMessage());
        }


    }


    //the same of getCategoiries insted of categoryid -> companyid
    public void getCompanyCategories(String companyId, final Context applicationContext, final Spinner gategory_spinner) {

        // gategory_spinner.setOnItemSelectedListener(this);


        final List<Category> lstCategories = new ArrayList<Category>();
        try {
            RequestParams params = new RequestParams();

            params.put("companyId", companyId);

            SmartmarketRestClient.get("GetCompanyCategories", params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    // If the response is JSONObject instead of expected JSONArray
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray parentArray) {
                    try {
                        Gson gson = new Gson();
                        lstCategories.add(new Category("-1", "All", "All Categories"));
                        for (int i = 0; i < parentArray.length(); i++) {

                            JSONObject finalObject = parentArray.getJSONObject(i);
                            String _id = finalObject.get("id").toString();
                            String categoryName = finalObject.getString("categoryName");
                            String description = finalObject.getString("description");


                            lstCategories.add(new Category(_id, categoryName, description));


                            // Do something with the response
                            System.out.println(categoryName);
                        }
                        List<StringWithTag> list = new ArrayList<StringWithTag>();
                        for (Category item : lstCategories) {
                            list.add(new StringWithTag(item.get_categoryName(),
                                    item.get_id()));
                        }

                        //ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_template,Gategory_list);
                        ArrayAdapter<StringWithTag> dataAdapter = new ArrayAdapter<StringWithTag>
                                (applicationContext, R.layout.spinner_template, list);

                        gategory_spinner.setAdapter(dataAdapter);


                    } catch (Exception e) {
                        Log.e("", e.getMessage());
                    }

                }
            });

        } catch (Exception e) {
            Log.e("", e.getMessage());
        }


    }


    public void logIn(final String Username, String Password, final Context mContext) {

        try {
            RequestParams params = new RequestParams();
            params.put("userName", Username);
            params.put("password", Password);
            SmartmarketRestClient.get("Login", params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    // If the response is JSONObject instead of expected JSONArray
                    try {
                        String UserId = "-1";
                        String success = "";


                        success = response.getString("success");
                        UserId = response.getString("UserId");
                        MyApp mApp = ((MyApp) mContext.getApplicationContext());

                        if (success.equals("true")) {

                            mApp.setUserId(UserId);
                            mApp.setUserName(Username);

                            Intent intent = new Intent(mContext, HomeActivity.class);
                            mContext.startActivity(intent);
                        } else {
                            mApp.setUserId("-1");
                            Toast.makeText(mContext, "Invalid username or password!"
                                    , Toast.LENGTH_LONG).show();
                        }

                    } catch (Exception e) {
                        Log.e("", e.getMessage());
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);

                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray parentArray) {


                }
            });

        } catch (Exception e) {
            Log.e("Login", e.getMessage());
        }
    }
    public void signUp(final String Username, final String Password,String email,String Phone, final Context mContext, final MyApp mApp) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserName", Username);
            jsonObject.put("Password", Password);
            jsonObject.put("Active", true);
            jsonObject.put("IsSystem", false);
            jsonObject.put("Email", email);
            jsonObject.put("Phone", Phone);
            jsonObject.put("Address", " ");
            jsonObject.put("UserType",2);
            ByteArrayEntity entity = new ByteArrayEntity(jsonObject.toString().getBytes("UTF-8"));
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));

            SmartmarketRestClient.postWithBody(mApp,"Signup", null, entity , new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    // If the response is JSONObject instead of expected JSONArray
                    try {
                        String UserId = "-1";
                        String success = "";


                        success = response.getString("success");
                        UserId = response.getString("UserId");
                        String message=response.getString("Message");


                        if (success.equals("true")) {

                            mApp.setUserId(UserId);
                            Intent intent = new Intent(mContext, MainActivity.class);
                            intent.putExtra("UserName", Username);
                            intent.putExtra("Password", Password);
                            mContext.startActivity(intent);
                        } else {
                            mApp.setUserId("-1");
                            Toast.makeText(mContext, "Signup failed:"+message, Toast.LENGTH_LONG).show();
                        }

                    } catch (Exception e) {
                        Log.e("", e.getMessage());
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);

                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray parentArray) {


                }
            });
        } catch (Exception e) {
            Log.e("signUp", e.getMessage());
        }
    }

    public void placeOrder(final Context mContext, final MyApp mApp) {
        try {

            List<Product> mlstBuyerProducts= mApp.getCart();
            List<OrderItem> orderItems = new ArrayList<OrderItem>();
            for (int i=0;i< mlstBuyerProducts.size();i++) {
                OrderItem orderItem = new OrderItem();
                orderItem.id = "00000000-0000-0000-0000-000000000000";
                orderItem.orderId = "00000000-0000-0000-0000-000000000000";
                orderItem.productId = mlstBuyerProducts.get(i).get_id();
                orderItem.count = Integer.parseInt(mlstBuyerProducts.get(i).get_count());
                orderItem.pricePerItem = 0;//ignore as i will get from db
                orderItems.add(orderItem);
            }
            Order order=new Order(Long.parseLong(mApp.getUserId()),orderItems);


            ByteArrayEntity entity = new ByteArrayEntity(order.toString().getBytes("UTF-8"));
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));

            SmartmarketRestClient.postWithBody(mApp,"PlaceOrder", null, entity , new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    // If the response is JSONObject instead of expected JSONArray
                    try {
                        String orderId = "-1";
                        String success = "";


                        success = response.getString("success");
                        orderId = response.getString("OrderId");
                        String message=response.getString("Message");


                        if (success.equals("true")) {
                            mApp.emptyCart();

                        } else {

                            Toast.makeText(mContext, "failed to add order"+message, Toast.LENGTH_LONG).show();
                        }

                    } catch (Exception e) {
                        Log.e("placeOrder", e.getMessage());
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);

                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray parentArray) {


                }
            });
        } catch (Exception e) {
            Log.e("placeOrder", e.getMessage());
        }
    }

    public void getRecommendations(final GridView grid,final Context mContext) {
        final List<Product> lstProducts = new ArrayList<Product>();
        final MyApp mApp = ((MyApp) mContext.getApplicationContext());
        try {

            List<Product> mlstBuyerProducts= mApp.getCart();
            List<OrderItem> orderItems = new ArrayList<OrderItem>();
            for (int i=0;i< mlstBuyerProducts.size();i++) {
                OrderItem orderItem = new OrderItem();
                orderItem.id = "00000000-0000-0000-0000-000000000000";
                orderItem.orderId = "00000000-0000-0000-0000-000000000000";
                orderItem.productId = mlstBuyerProducts.get(i).get_id();
                orderItem.count = Integer.parseInt(mlstBuyerProducts.get(i).get_count());
                orderItem.pricePerItem = 0;//ignore as i will get from db
                orderItems.add(orderItem);
            }
            Order order=new Order(Long.parseLong(mApp.getUserId()),orderItems);


            ByteArrayEntity entity = new ByteArrayEntity(order.toString().getBytes("UTF-8"));
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));

            SmartmarketRestClient.postWithBody(mApp,"GetRecommendations", null, entity , new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);

                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray parentArray) {
                    try {
                        Gson gson = new Gson();
                        for (int i = 0; i < parentArray.length(); i++) {

                            JSONObject finalObject = parentArray.getJSONObject(i);

                            String _id = finalObject.get("id").toString();
                            String _rate = finalObject.getString("rate");
                            String _description = finalObject.getString("description");
                            String _productName = finalObject.getString("productName");
                            String _companyName = finalObject.getJSONObject("company").getString("companyName");
                            String _subCategoryName = finalObject.getJSONObject("subCategory").getString("subCategoryName");
                            String _price = finalObject.getString("price");
                            String _availableStock = finalObject.getString("availableStock");
                            String _image = finalObject.getString("imageURL");


                            lstProducts.add(new Product(_id, _companyName, _subCategoryName, _productName, _description, _price, _availableStock, _image, _rate));

                        }

                        //grid=(GridView)findViewById(R.id.gridView);
                        if (lstProducts.size() > 0) {

                            grid.setAdapter(new ProductAdapter(mContext, lstProducts,mApp));
                        }

                    } catch (Exception e) {
                        Log.e("getRecommendedProducts", e.getMessage());
                    }

                }
            });
        } catch (Exception e) {
            Log.e("getRecommendedProducts", e.getMessage());
        }
    }
}