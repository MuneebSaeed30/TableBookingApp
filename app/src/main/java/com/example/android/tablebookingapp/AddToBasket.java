package com.example.android.tablebookingapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AddToBasket extends Fragment {
    private ListView lv;
    private ArrayList<Menu> menu= new ArrayList<>();
    private ArrayAdapter arrayAdapter;
    private TextView item, itemquantity,itemprice;




    public AddToBasket() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View v=inflater.inflate(R.layout.fragment_add_to_basket, container, false);

        Bundle bundle1= getArguments();
        String itemname=bundle1.getString("item");
        int itemquan=bundle1.getInt("itemquantity");
        int  itemrs=bundle1.getInt("itemprice");



        lv= (ListView) v.findViewById(R.id.lv);
        Menu c1= new Menu(itemname,itemquan,itemrs);
        menu.add(c1);

        lv.setAdapter(new BasketAdapter(this.getContext(),menu));
        return v;
    }

}
