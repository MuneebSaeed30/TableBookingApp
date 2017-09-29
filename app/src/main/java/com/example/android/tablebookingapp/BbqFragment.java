package com.example.android.tablebookingapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class BbqFragment extends Fragment {

    private RecyclerView recyclerView;
    private final ArrayList<Menu> menu= new ArrayList<>();



    public BbqFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_bbq, container, false);

        recyclerView= (RecyclerView) v.findViewById(R.id.FoodMenu);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(this.getContext()));
        ItemsAdapter menu1Adapter= new ItemsAdapter(menu,this.getContext());
        recyclerView.setAdapter(menu1Adapter);

        int[] covers = new int[]{
                R.drawable.tikka,
                R.drawable.malaeboti,
                R.drawable.reshmikabab,
                R.drawable.chargha,
                R.drawable.roll };


        Menu c1= new Menu("Chicken Tikka","Handi, Karahi, Rogni Naan..",covers[0], 1200);
        menu.add(c1);

        Menu c2= new Menu("Malai Boti","Chargha, Tikka, Seekh Kabab..",covers[1],1200);
        menu.add(c2);

        Menu c3= new Menu("Reshmi Kabab","Chowmein, Singaporian Rice..",covers[2], 1200);
        menu.add(c3);

        Menu c4= new Menu("Chargha","Pizza, Lasagne..",covers[3], 1200);
        menu.add(c4);

        Menu c5= new Menu("Beef Roll","Beef Cheese Burger, Zinger Stacker..",covers[4], 1200);
        menu.add(c5);


        return v;
    }

    public static BbqFragment getInstance(){

        BbqFragment iF = new BbqFragment();
        return iF;
    }






}

