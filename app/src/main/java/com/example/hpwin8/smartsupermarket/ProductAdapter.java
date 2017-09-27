package com.example.hpwin8.smartsupermarket;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.ImageView;

import android.widget.TextView;


import android.widget.*;

import com.example.hpwin8.smartsupermarket.Info.Product;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by taha.amin on 6/24/2017.
 */

public class ProductAdapter extends BaseAdapter {
    private Context mContext;
    private List<Product> mlstProducts;

    final MyApp mApp ;

    public ProductAdapter(Context c, List<Product> lstProducts,  MyApp mApp1) {
        mContext = c;
        mlstProducts = lstProducts;
        mApp =((MyApp) mContext.getApplicationContext());;
    }

    public ProductAdapter(Context c) {
        mContext = c;
        mApp = ((MyApp) mContext.getApplicationContext());
        mlstProducts = mApp.getCart();
    }

    @Override
    public int getViewTypeCount() {

        return getCount();
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }

    public int getCount() {
        return mlstProducts.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView( final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        RecordHolder holder = new RecordHolder();
        if (row == null) {

            LayoutInflater inflater = ((AppCompatActivity) mContext).getLayoutInflater();

            row = inflater.inflate(R.layout.grid_single, parent, false);



            holder.txtTitle = (TextView) row.findViewById(R.id.item_text);

            holder.imageItem = (ImageView) row.findViewById(R.id.item_image);

            holder.txtPrice = (TextView) row.findViewById(R.id.pricetxt);


            holder.minus = (Button) row.findViewById(R.id.minusbutton);
            holder.plus = (Button) row.findViewById(R.id.pulsebutton);
            holder.cart = (Button) row.findViewById(R.id.addtocartbtn);
            holder.Count = (TextView) row.findViewById(R.id.Count);


            row.setTag(holder);

        } else {

            holder = (RecordHolder) row.getTag();

        }

        holder.txtTitle.setText(mlstProducts.get(position).get_productName());

        holder.txtPrice.setText(mlstProducts.get(position).get_price());
        holder.productId = mlstProducts.get(position).get_id();
        holder.Position = position;
        holder.Count.setText(mlstProducts.get(position).get_count());



        Picasso.with(mContext).load(mlstProducts.get(position).get_image()).into(holder.imageItem);


        holder.minus.setOnClickListener(new View.OnClickListener(

        ) {
            @Override
            public void onClick(View v) {

                RecordHolder viewHolder = (RecordHolder)((View) v.getParent().getParent()).getTag();

                int count = Integer.parseInt(viewHolder.Count.getText().toString());
                if(count>=1) {
                    count = count - 1;
                    viewHolder.Count.setText(Integer.toString(count));


                    Product product = mlstProducts.get(viewHolder.Position);

                    mApp.removeFromCart(product);
                    Toast.makeText(mContext, "item removed from cart -", Toast.LENGTH_LONG).show();
                }

            }
        });

        holder.plus.setOnClickListener(new View.OnClickListener(

        ) {
            @Override
            public void onClick(View v) {
                RecordHolder viewHolder = (RecordHolder)((View) v.getParent().getParent()).getTag();

                int count = Integer.parseInt(viewHolder.Count.getText().toString());

                count = count + 1;
                viewHolder.Count.setText(Integer.toString(count));


                Product product = mlstProducts.get(viewHolder.Position);

                mApp.addToCart(product);
                Toast.makeText(mContext, "item added to cart +", Toast.LENGTH_LONG).show();


            }
        });
        holder.cart.setOnClickListener(new View.OnClickListener(

        ) {
            @Override
            public void onClick(View v) {

                RecordHolder viewHolder = (RecordHolder)((View) v.getParent().getParent()).getTag();

                int count = Integer.parseInt(viewHolder.Count.getText().toString());
                if(count>=1) {
                    count = count - 1;
                    viewHolder.Count.setText(Integer.toString(count));


                    Product product = mlstProducts.get(viewHolder.Position);

                    mApp.removeFromCart(product);
                    Toast.makeText(mContext, "item added to cart +", Toast.LENGTH_LONG).show();
                }

            }
        });

        return row;


    }
    static class RecordHolder {
        TextView txtTitle;
        TextView txtPrice;
        ImageView imageItem;
        Button minus, plus,cart;
        TextView Count;
        String productId;
        int Position;
    }

}
