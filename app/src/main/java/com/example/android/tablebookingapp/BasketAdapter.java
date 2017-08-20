package com.example.android.tablebookingapp;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BasketAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private ArrayList<Menu> menu= new ArrayList<>();
    private TextView item, itemquantity,itemprice;
    private Context context;

    public BasketAdapter(Context context , ArrayList<Menu> menu) {

        this.context= context;
        inflater = LayoutInflater.from(context);
        this.menu = menu;
    }

    public BasketAdapter() {
    }





    @Override
    public int getCount() {
        return menu.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int  position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        View v=convertView;
        if(convertView==null)
            v = inflater.inflate(R.layout.layout_add_to_basket, null);

        item =(TextView) v.findViewById(R.id.itm);
        itemquantity =(TextView) v.findViewById(R.id.quan);
        itemprice =(TextView) v.findViewById(R.id.rs);


        item.setText(menu.get(position).getName());
        itemquantity.setText(String.valueOf(menu.get(position).getQuantity()));
        itemprice.setText(String.valueOf(menu.get(position).getPrice()));


        return v;
    }
}
