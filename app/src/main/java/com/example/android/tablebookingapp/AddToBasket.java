package com.example.android.tablebookingapp;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddToBasket extends Fragment {
    private ListView lv;
    private ArrayList<Menu> menu= new ArrayList<Menu>();
    private Button btnShop, btnCheckout;
    String itemname;
    int itemquan, itemrs;
    Bundle bundle1;
    private ArrayAdapter<Menu> adapter;
    BasketAdapter basketadapter;



    public AddToBasket() {
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View v=inflater.inflate(R.layout.fragment_add_to_basket, container, false);



        bundle1= getArguments();
        itemname=bundle1.getString("item");
        itemquan=bundle1.getInt("itemquantity");
        itemrs=bundle1.getInt("itemprice");

        basketadapter= new BasketAdapter(this.getActivity(),menu);
        lv= (ListView) v.findViewById(R.id.lv);
        lv.setAdapter(basketadapter);
        Menu c1=new Menu(itemname,itemquan,itemrs);
        addItem(c1);

        //basketadapter.SetModel(menu);


        btnShop = (Button) v.findViewById(R.id.shopping);
        btnCheckout = (Button) v.findViewById(R.id.checkout);

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, int position , long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setCancelable(false);
                builder.setTitle("Delete Item");
                builder.setInverseBackgroundForced(true);
                builder.setMessage("Are You Sure To Delete This Item");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getContext(),"Item Deleted", Toast.LENGTH_LONG).show();



                            }
                        });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
                return false;
            }

        });




        btnShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Menu1Fragment menu1Fragment = new Menu1Fragment();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.content_layout, menu1Fragment, menu1Fragment.getTag())
                        .addToBackStack(null)
                        .commit();
            }
        });

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setCancelable(false);
                builder.setTitle("Check Out");
                builder.setMessage("Are You Sure?");
                builder.setInverseBackgroundForced(true);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(),"Checked out. Thankyou For Shopping", Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        return v;
    }

    public void addItem(Menu item){

        menu.add(item);
        basketadapter.notifyDataSetChanged();
    }







}
