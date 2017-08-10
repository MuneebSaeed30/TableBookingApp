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


public class ContinentalFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<Menu> menu= new ArrayList<>();



    public ContinentalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_continental, container, false);

        recyclerView= (RecyclerView) v.findViewById(R.id.FoodMenu);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(this.getContext()));
        Menu1Adapter menu1Adapter= new Menu1Adapter(menu,this.getContext());
        recyclerView.setAdapter(menu1Adapter);

        int[] covers = new int[]{
                R.drawable.mhandi,
                R.drawable.brain,
                R.drawable.liver,
                R.drawable.phandi,
                R.drawable.mkarahi};


        Menu c1= new Menu("Makhni Handi","Handi, Karahi, Rogni Naan..",covers[0]);
        menu.add(c1);

        Menu c2= new Menu("Brain Masala","Chargha, Tikka, Seekh Kabab..",covers[1]);
        menu.add(c2);

        Menu c3= new Menu("Liver","Chowmein, Singaporian Rice..",covers[2]);
        menu.add(c3);

        Menu c4= new Menu("Paneeri Reshmi Handi","Pizza, Lasagne..",covers[3]);
        menu.add(c4);

        Menu c5= new Menu("Karahi","Beef Cheese Burger, Zinger Stacker..",covers[4]);
        menu.add(c5);

        return v;
    }

}
