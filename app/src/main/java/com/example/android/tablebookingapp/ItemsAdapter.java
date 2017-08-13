package com.example.android.tablebookingapp;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter <ItemsAdapter.ViewHolder> {


    private List<Menu> menu;
    private Context context;
    int minteger = 0;

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
        public TextView catQuantity;

        public ViewHolder(View itemView) {
            super(itemView);

            catName = (TextView) itemView.findViewById(R.id.item_name);
            catDesc = (TextView) itemView.findViewById(R.id.item_desc);
            catImage = (ImageView) itemView.findViewById(R.id.item_image);
            catQuantity = (TextView) itemView.findViewById(R.id.item_quantity);
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
               int i= increaseInteger();
                catQuantity.setText(String.valueOf(i));

            } else if (view.getId() == dec.getId()) {
                int i=decreaseInteger();
                catQuantity.setText(String.valueOf(i));

            }else if (view.getId() == btnCart.getId()) {

                Toast.makeText(view.getContext() , "Added To Cart" , Toast.LENGTH_LONG).show();


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
