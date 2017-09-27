package com.example.hpwin8.smartsupermarket;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hpwin8.smartsupermarket.Info.*;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import android.util.Log;
import android.widget.Toast;

import cz.msebera.android.httpclient.Header;


public class HomeActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
    Spinner gategory_spinner;
    GridView gridView;

    List<Company> mlstCompany;
    TextView tv_title;
    EditText searchtxt;
    Button cartbtn,home,searchbtn;
    String flagvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SmartmarketRestClientUsage oSmartmarketRestClientUsage = new SmartmarketRestClientUsage();

        //------ Spinner------
        gategory_spinner = (Spinner) findViewById(R.id.gategoryspinner);
        gategory_spinner.setOnItemSelectedListener(this);
        oSmartmarketRestClientUsage.getCategories("", this, gategory_spinner);


        //-------------GridView--------
        gridView = (GridView) findViewById(R.id.gridView);
        oSmartmarketRestClientUsage.getCompanies("", this, gridView);

        //------cartButton
        cartbtn = (Button) findViewById(R.id.cartButton);
        cartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, CartActivity.class);
                startActivity(i);
            }
        });

        //-----Home button
        home = (Button) findViewById(R.id.homebtn);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //------Search button
        searchbtn = (Button) findViewById(R.id.searchbtn);
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                searchtxt = (EditText) findViewById(R.id.searchTxt);
                String searchvalue = searchtxt.getText().toString();
                /*Send search  gategory value  to ProductActivity */
                if (searchvalue != null && !searchvalue.isEmpty()) {

                    flagvalue = "2";

                    viewProduct(searchvalue, "-1", "", flagvalue, "", "");
                } else {
                    Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                }

            }
        });

    }

        //-----Radio button
    public void onRadioButtonClicked(View view) {   //here to change the text of title TextView based on what radioButto Clicked
        // if All Market or nearby market
        boolean checked = ((RadioButton) view).isChecked();
        tv_title = (TextView) findViewById(R.id.title);
        switch (view.getId()) {
            case R.id.allRadioButton:
                if (checked)
                    tv_title.setText(R.string.allmarket);
                break;


            case R.id.nearbyRadioButton:
                if (checked)
                    tv_title.setText(R.string.nearbymarket);

                break;


        }

    }




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        try {
            gategory_spinner = (Spinner) findViewById(R.id.gategoryspinner);

        /*Send Selcted gategory value  to ProductActivity */
            String categoryId = ((StringWithTag) gategory_spinner.getSelectedItem()).value();
            if (categoryId != "-1") {
                flagvalue = "1";
                String categoryName = ((StringWithTag) gategory_spinner.getSelectedItem()).toString();


                viewProduct("", categoryId, categoryName, flagvalue, "", "");
            }
        }
        catch (Exception e) {
            Log.e("onItemSelected", e.getMessage());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void viewProduct(String seachValue, String categoryId, String categoryName, String flagValue, String companyId, String  companyName )
    {
        Intent intent = new Intent(HomeActivity.this, ProductsActivity.class);

        intent.putExtra("searchvalue", seachValue);
        intent.putExtra("categoryId", categoryId);
        intent.putExtra("categoryName", categoryName);
        intent.putExtra("flagvalue", flagValue);
        intent.putExtra("companyId", companyId);
        intent.putExtra("companyName", companyName);
        startActivity(intent);
    }


}