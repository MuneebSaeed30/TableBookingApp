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
    private Context context;

    public BasketAdapter(Context context) {
        this.context = context;
    }

    public BasketAdapter(Context context , ArrayList<Menu> menu) {

        this.context= context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        ViewHolder holder;
        if(convertView==null) {
            holder= new ViewHolder();
            v = inflater.inflate(R.layout.layout_add_to_basket, null);
            holder.item =(TextView) v.findViewById(R.id.itm);
            holder.itemquantity =(TextView) v.findViewById(R.id.quan);
            holder.itemprice =(TextView) v.findViewById(R.id.rs);
            v.setTag(holder);
        }else{
            holder = (ViewHolder) v.getTag();
        }


        holder.item.setText(menu.get(position).getName());
        holder.itemquantity.setText(String.valueOf(menu.get(position).getQuantity()));
        holder.itemprice.setText(String.valueOf(menu.get(position).getPrice()));

        return v;
    }
    class ViewHolder{
        private TextView item, itemquantity,itemprice;


    }
}
