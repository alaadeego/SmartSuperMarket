package com.example.hpwin8.smartsupermarket;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hpwin8.smartsupermarket.Info.StringWithTag;


public class ProductsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    GridView gridView;
    Spinner gategory_spinner;
    //Button sortbtn;
    String sortFlag="0";

    SmartmarketRestClientUsage oSmartmarketRestClientUsage;
    String searchvalue="",companyId="",CategoryID="",flagvalue="",CategoryName="",companyName="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        try {
            //Grid view
            Intent intent = getIntent();
            searchvalue = intent.getStringExtra("searchvalue");
            CategoryID = intent.getStringExtra("categoryId");
            CategoryName = intent.getStringExtra("categoryName");
            flagvalue = intent.getStringExtra("flagvalue");
            companyId = intent.getStringExtra("companyId");
            companyName = intent.getStringExtra("companyName");

            if (CategoryID == "-1") {
                CategoryID = "";
            }

        }
        catch(Exception e) {
            Log.e("Products", e.getMessage());
        }

        //gridview of search and spiner  any comanyimage
        gridView = (GridView) findViewById(R.id.gridView);
        oSmartmarketRestClientUsage = new SmartmarketRestClientUsage();
        oSmartmarketRestClientUsage.getProducts(companyId, CategoryID, searchvalue, this, gridView);

        //-----sort Button
      /*  sortbtn= (Button) findViewById(R.id.sortbtn);
        sortbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortFlag="1";
                oSmartmarketRestClientUsage.getProducts(companyId,CategoryID,searchvalue,getApplicationContext(),gridView,sortFlag);

            }
        });

*/
        //Spinner
        gategory_spinner = (Spinner) findViewById(R.id.gategoryspinner);
        oSmartmarketRestClientUsage.getCompanyCategories(companyId, this, gategory_spinner);
        gategory_spinner.setOnItemSelectedListener(this);


        //..........................


     /*   gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(ProductsActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
        */
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        gategory_spinner = (Spinner) findViewById(R.id.gategoryspinner);

        /*Send Selcted gategory value  to ProductActivity */
        String categoryId = ((StringWithTag) gategory_spinner.getSelectedItem()).value();
        if(categoryId != "-1") {
            String flagvalue="1";
            String categoryName = ((StringWithTag) gategory_spinner.getSelectedItem()).toString();


            viewProduct("", categoryId,  categoryName,  flagvalue,"","" );
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void viewProduct(String seachValue, String categoryId, String categoryName, String flagValue, String companyId, String  companyName )
    {
        Intent intent = new Intent(ProductsActivity.this, ProductsActivity.class);

        intent.putExtra("searchvalue", seachValue);
        intent.putExtra("categoryId", categoryId);
        intent.putExtra("categoryName", categoryName);
        intent.putExtra("flagvalue", flagValue);
        intent.putExtra("companyId", companyId);
        intent.putExtra("companyName", companyName);
        startActivity(intent);
    }
}
