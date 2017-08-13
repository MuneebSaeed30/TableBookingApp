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


/**
 * A simple {@link Fragment} subclass.
 */
public class ItalianFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<Menu> menu= new ArrayList<>();



    public ItalianFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_italian, container, false);

        recyclerView= (RecyclerView) v.findViewById(R.id.FoodMenu);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(this.getContext()));
        ItemsAdapter itemsAdapter = new ItemsAdapter(menu, this.getContext());
        recyclerView.setAdapter(itemsAdapter);

        int[] covers = new int[]{
                R.drawable.pizza,
                R.drawable.lasagne,
                R.drawable.bread,
                R.drawable.pasta,
                R.drawable.beefpizza};


        Menu c1= new Menu("Chicken Pizza","Handi, Karahi, Rogni Naan..",covers[0]);
        menu.add(c1);

        Menu c2= new Menu("Lasagne","Chargha, Tikka, Seekh Kabab..",covers[1]);
        menu.add(c2);

        Menu c3= new Menu("Garlic Bread","Chowmein, Singaporian Rice..",covers[2]);
        menu.add(c3);

        Menu c4= new Menu("Pasta","Pizza, Lasagne..",covers[3]);
        menu.add(c4);

        Menu c5= new Menu("Beef Pizza","Beef Cheese Burger, Zinger Stacker..",covers[4]);
        menu.add(c5);
        return v;

    }
}
