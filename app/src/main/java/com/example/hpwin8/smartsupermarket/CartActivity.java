package com.example.hpwin8.smartsupermarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;


public class CartActivity extends AppCompatActivity {
    GridView gridView,gridViewRecommended;
    Button deliverbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        try {

            gridView = (GridView) findViewById(R.id.gridView);

            gridView.setAdapter(new ProductAdapter(this));
            deliverbtn = (Button) findViewById(R.id.Deliverbutton);
            deliverbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(CartActivity.this, DeliveryActivity.class);
                    startActivity(i);

                }
            });

            gridViewRecommended = (GridView) findViewById(R.id.gridViewRecommended);
            SmartmarketRestClientUsage oSmartmarketRestClientUsage = new SmartmarketRestClientUsage();
            oSmartmarketRestClientUsage.getRecommendations(gridViewRecommended, this);
        } catch (Exception e) {
            Log.e("CartActivity", e.getMessage());
        }
    }
}
