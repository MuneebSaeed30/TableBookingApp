package com.example.android.tablebookingapp;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView catName;
        public TextView catDesc;
        public ImageView catImage;


        public ViewHolder(View itemView) {
            super(itemView);

            catName = (TextView) itemView.findViewById(R.id.item_name);
            catDesc = (TextView) itemView.findViewById(R.id.item_desc);
            catImage = (ImageView) itemView.findViewById(R.id.item_image);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            int i= getAdapterPosition();
            AppCompatActivity activity = (AppCompatActivity) view.getContext();

            if (i== 0){
                ContinentalFragment continentalFragment = new ContinentalFragment();
                FragmentManager manager = activity.getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.content_layout, continentalFragment, continentalFragment.getTag()).commit();
            }
            if (i== 1){
                BbqFragment bbqFragment = new BbqFragment();
                FragmentManager manager = activity.getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.content_layout, bbqFragment , bbqFragment .getTag()).commit();
            }
            if (i== 2){
                ChineseFragment chineseFragment = new ChineseFragment();
                FragmentManager manager = activity.getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.content_layout, chineseFragment, chineseFragment.getTag()).commit();
            }
            if (i== 3){
                ItalianFragment  italianFragment = new  ItalianFragment();
                FragmentManager manager = activity.getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.content_layout,  italianFragment,  italianFragment.getTag()).commit();
            }
            if (i== 4){
                FastfoodFragment fastfoodFragment = new FastfoodFragment();
                FragmentManager manager = activity.getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.content_layout, fastfoodFragment, fastfoodFragment.getTag()).commit();
            }






        }
    }
}


