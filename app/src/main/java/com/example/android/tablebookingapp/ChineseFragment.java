package com.example.android.tablebookingapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class ChineseFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<Menu> menu= new ArrayList<>();



    public ChineseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chinese, container, false);

        recyclerView= (RecyclerView) v.findViewById(R.id.FoodMenu);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(this.getContext()));
        ItemsAdapter itemsAdapter = new ItemsAdapter(menu, this.getContext());
        recyclerView.setAdapter(itemsAdapter);

        int[] covers = new int[]{
                R.drawable.singaporian,
                R.drawable.friedrice,
                R.drawable.chowmein,
                R.drawable.macroni,
                R.drawable.spaghetti};


        Menu c1= new Menu("Singaporian Rice","Handi, Karahi, Rogni Naan..",covers[0]);
        menu.add(c1);

        Menu c2= new Menu("Fried Rice","Chargha, Tikka, Seekh Kabab..",covers[1]);
        menu.add(c2);

        Menu c3= new Menu("Chowmein","Chowmein, Singaporian Rice..",covers[2]);
        menu.add(c3);

        Menu c4= new Menu("Macroni","Pizza, Lasagne..",covers[3]);
        menu.add(c4);

        Menu c5= new Menu("Spaghetti","Beef Cheese Burger, Zinger Stacker..",covers[4]);
        menu.add(c5);

        return v;

    }
}
