package com.example.hpwin8.smartsupermarket;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hpwin8.smartsupermarket.Info.Company;
import com.example.hpwin8.smartsupermarket.Info.StringWithTag;
import com.squareup.picasso.Picasso;


import java.util.List;

/**
 * Created by HP WIN 8 on 6/27/2017.
 */

public class CompanyAdapter extends BaseAdapter {
    private Context mContext;
    private List<Company> mlstCompany;

    public CompanyAdapter(Context c, List<Company> lstCompany) {
        mContext = c;
        mlstCompany = lstCompany;
    }


    @Override
    public int getCount() { return mlstCompany.size(); }

    @Override
    public Object getItem(int position) { return null; }

    @Override
    public long getItemId(int position) { return 0; }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        RecordHolder holder = null;

        if (row == null) {

            LayoutInflater inflater = ((AppCompatActivity) mContext).getLayoutInflater();

            row = inflater.inflate(R.layout.company_single, parent, false);

            holder = new RecordHolder();

            holder.txtTitle = (TextView) row.findViewById(R.id.txtCompany);

            holder.imageItem = (ImageButton) row.findViewById(R.id.imageCompany);
            holder.imageItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String categoryId = Integer.toString(position);
                    Intent intent = new Intent( mContext,ProductsActivity.class);
                    intent.putExtra("searchvalue", "");
                    intent.putExtra("categoryId", "");
                    intent.putExtra("categoryName", "");
                    intent.putExtra("flagvalue", "");
                    intent.putExtra("companyId", mlstCompany.get(position).get_id());
                    intent.putExtra("companyName", mlstCompany.get(position).get_companyName());


                    mContext.startActivity(intent);



                }
            });


            row.setTag(holder);

        } else {

            holder = (RecordHolder) row.getTag();

        }

        holder.txtTitle.setText(mlstCompany.get(position).get_companyName());

        Picasso.with(mContext).load(mlstCompany.get(position).get_imageUrl()).into(holder.imageItem);

        return row;


    }
    static class RecordHolder {
        TextView txtTitle;
        ImageButton imageItem;

    }
}
