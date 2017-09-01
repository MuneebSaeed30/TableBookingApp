package com.example.android.tablebookingapp;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import model.CartItems;

public class ItemsAdapter extends RecyclerView.Adapter <ItemsAdapter.ViewHolder> {


    private List<Menu> menu;

    private Context context;
    int minteger = 0;
    int i=0;
    public ArrayList<CartItems> itemOnCart= new ArrayList<>();

    public ItemsAdapter(List<Menu> menu, Context context) {
        this.menu = menu;
        this.context = context;
    }


    @Override
    public ItemsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Menu menus = menu.get(position);

        holder.catName.setText(menus.getName());
        holder.catDesc.setText(menus.getDiscription());
        holder.catPrice.setText(String.valueOf(menus.getPrice()));
        holder.catQuantity.setText(String.valueOf(minteger));
        Picasso.with(context)
                .load(menus.getImage())
                .placeholder(R.drawable.logo)
                .into(holder.catImage);

    }

    @Override
    public int getItemCount() {
        return menu.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView catName;
        public TextView catDesc;
        public ImageView catImage;
        public Button inc, dec, btnCart;
        public TextView catQuantity,catPrice;

        public ViewHolder(View itemView) {
            super(itemView);

            catName = (TextView) itemView.findViewById(R.id.item_name);
            catDesc = (TextView) itemView.findViewById(R.id.item_desc);
            catImage = (ImageView) itemView.findViewById(R.id.item_image);
            catQuantity = (TextView) itemView.findViewById(R.id.item_quantity);
            catPrice = (TextView) itemView.findViewById(R.id.item_price);
            inc = (Button) itemView.findViewById(R.id.increase);
            dec = (Button) itemView.findViewById(R.id.decrease);
            btnCart = (Button) itemView.findViewById(R.id.btn_cart);


            inc.setOnClickListener(this);
            dec.setOnClickListener(this);
            btnCart.setOnClickListener(this);

        }




        @Override
        public void onClick(View view) {

            if (view.getId() == inc.getId()) {

                 i= increaseInteger();
                catQuantity.setText(String.valueOf(i));

            } else if (view.getId() == dec.getId()) {
                i=decreaseInteger();
                catQuantity.setText(String.valueOf(i));

            } else if (view.getId() == btnCart.getId()) {

                AppCompatActivity activity = (AppCompatActivity) view.getRootView().getContext();
                CartItems cartItems =new CartItems();
                cartItems.setItemName(menu.get(getAdapterPosition()).getName());
                cartItems.setItemQuantity(String.valueOf(i));
                int a=menu.get(getAdapterPosition()).getPrice();
                int price= a*i;
                cartItems.setItemPrice(String.valueOf(price));
                itemOnCart.add(cartItems);
                Bundle bundle1= new Bundle();
               bundle1.putSerializable("CartItems",itemOnCart);
                 /*AddToBasket atb= new AddToBasket();
                atb.setArguments(bundle1);
                FragmentManager manager= activity.getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.content_layout, atb, atb.getTag()).commit();*/

                Toast.makeText(view.getContext() , "Added To Cart" , Toast.LENGTH_LONG).show();
                if(itemOnCart.size()==3){
                    AddToBasket atb= new AddToBasket();
                    atb.setArguments(bundle1);
                    FragmentManager manager= activity.getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.content_layout, atb, atb.getTag()).commit();

                }


            }

        }


    }


    public int increaseInteger() {
        minteger = minteger + 1;
        return minteger;
    }

    public int decreaseInteger() {
        if (minteger == 0) {
            return minteger;

        } else {
            minteger = minteger - 1;
            return minteger;

        }
    }

}
