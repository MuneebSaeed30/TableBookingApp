package com.example.android.tablebookingapp;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import model.CartItems;

public class BasketAdapter extends ArrayAdapter<CartItems> {
    private Activity activity;
    private LayoutInflater inflater;
    private Context context;
    static ArrayList<CartItems>itemOnCart= new ArrayList<>();



    public BasketAdapter(Context context, ArrayList<CartItems> itemOnCart) {
        super(context,R.layout.layout_add_to_basket,itemOnCart);
        this.context= context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.itemOnCart= itemOnCart;

    }





    @Override
    public int getCount() {
        return itemOnCart.size();
    }

    @Override
    public long getItemId(int  position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {


        ViewHolder holder;

            if(convertView==null) {
                holder= new ViewHolder();
                convertView = inflater.inflate(R.layout.layout_add_to_basket, null);
                holder.item =(TextView) convertView.findViewById(R.id.itm);
                holder.itemQuantity =(TextView) convertView.findViewById(R.id.quan);
                holder.itemPrice =(TextView) convertView.findViewById(R.id.rs);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }


            holder.item.setText(itemOnCart.get(position).getItemName());
            holder.itemQuantity.setText(String.valueOf(itemOnCart.get(position).getItemQuantity()));
            holder.itemPrice.setText(String.valueOf(itemOnCart.get(position).getItemPrice()));

        return convertView;
    }

    private static class ViewHolder{
        private TextView item, itemQuantity,itemPrice;


    }
}
