package com.example.android.tablebookingapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class Menu1Fragment extends Fragment implements RecyclerView.OnItemTouchListener {

    private RecyclerView recyclerView;
    private List<com.example.android.tablebookingapp.Menu>  menu= new ArrayList<>();


    public Menu1Fragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_menu1, container, false);


        recyclerView= (RecyclerView) view.findViewById(R.id.FoodMenu);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(this.getContext()));
        Menu1Adapter menu1Adapter= new Menu1Adapter(menu,this.getContext());
        recyclerView.setAdapter(menu1Adapter);

        int[] covers = new int[]{
                R.drawable.continental,
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


    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
    return  false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}



       /*
            @Override
            public void onClick(View view, final int position) {



                if (i== 0){
                    ContinentalFragment continentalFragment = new ContinentalFragment();
                    FragmentManager manager = getFragmentManager();
                    manager.beginTransaction().replace(R.id.content_layout, continentalFragment, continentalFragment.getTag()).commit();
                }
                if (i== 1){
                    BbqFragment bbqFragment = new BbqFragment();
                    FragmentManager manager = getFragmentManager();
                    manager.beginTransaction().replace(R.id.content_layout, bbqFragment , bbqFragment .getTag()).commit();
                }
                if (i== 2){
                    ChineseFragment chineseFragment = new ChineseFragment();
                    FragmentManager manager = getFragmentManager();
                    manager.beginTransaction().replace(R.id.content_layout, chineseFragment, chineseFragment.getTag()).commit();
                }
                if (i== 3){
                    ItalianFragment  italianFragment = new  ItalianFragment();
                    FragmentManager manager = getFragmentManager();
                    manager.beginTransaction().replace(R.id.content_layout,  italianFragment,  italianFragment.getTag()).commit();
                }
                if (i== 4){
                    FastfoodFragment fastfoodFragment = new FastfoodFragment();
                    FragmentManager manager = getFragmentManager();
                    manager.beginTransaction().replace(R.id.content_layout, fastfoodFragment, fastfoodFragment.getTag()).commit();
                }

            }
        }));



*/

