package com.example.android.tablebookingapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class Menu1Adapter extends RecyclerView.Adapter<Menu1Adapter.ViewHolder> {

    private  List<Menu> menu;
    private Context context;

    public Menu1Adapter(List<Menu> menu, Context context) {
        this.menu = menu;
        this.context = context;
    }


    @Override
    public Menu1Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu1_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Menu1Adapter.ViewHolder holder, int position) {
        Menu menus= menu.get(position);
        holder.catName.setText(menus.getName());
       holder.catDesc.setText(menus.getDiscription());

         Picasso.with(context)
                        .load(menus.getImage())
                        .placeholder(R.drawable.logo)
                        .into(holder.catImage);






    }

    @Override
    public int getItemCount() {
        return menu.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView catName;
        public TextView catDesc;
        public ImageView catImage;


        public ViewHolder(View itemView) {
            super(itemView);

            catName = (TextView) itemView.findViewById(R.id.item_name);
            catDesc = (TextView) itemView.findViewById(R.id.item_desc);
            catImage = (ImageView) itemView.findViewById(R.id.item_image);


        }
    }
}


