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


public class SeatFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Menu> menu= new ArrayList<>();


    public SeatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_menu1, container, false);
        recyclerView= (RecyclerView) view.findViewById(R.id.FoodMenu);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(this.getContext()));
        Menu1Adapter menu1Adapter= new Menu1Adapter(menu,this.getContext());
        recyclerView.setAdapter(menu1Adapter);

        int[] covers = new int[]{
                R.drawable.bbq,
                R.drawable.bbq,
                R.drawable.chinese,
                R.drawable.italian,
                R.drawable.fastfood};


        Menu c1= new Menu("Continental","Handi, Karahi, Rogni Naan..",covers[0]);
        menu.add(c1);

        Menu c2= new Menu("BarBQ","Chargha, Tikka, Seekh Kabab..",covers[1]);
        menu.add(c2);

        Menu c3= new Menu("Chinese","Chowmein, Singaporian Rice..",covers[2]);
        menu.add(c3);

        Menu c4= new Menu("Italian","Pizza, Lasagne..",covers[3]);
        menu.add(c4);

        Menu c5= new Menu("FastFood","Beef Cheese Burger, Zinger Stacker..",covers[4]);
        menu.add(c5);

        return view;




    }

}
