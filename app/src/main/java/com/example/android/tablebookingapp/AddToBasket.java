package com.example.android.tablebookingapp;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import model.CartItems;

public class AddToBasket extends Fragment {
    private ListView lv;
    private Button btnShop, btnCheckout;
    BasketAdapter basketadapter;
    public TextView tp;
     int total=0;
    int i=0;
    final DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth nAuth;






    public AddToBasket() {
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View v=inflater.inflate(R.layout.fragment_add_to_basket, container, false);
        nAuth = FirebaseAuth.getInstance();

        final ArrayList<CartItems> itemOnCart= (ArrayList<CartItems>)getArguments().getSerializable("CartItems");
          tp= (TextView) v.findViewById(R.id.total);


        basketadapter= new BasketAdapter(this.getActivity(),itemOnCart);
        lv= (ListView) v.findViewById(R.id.lv);
        lv.setAdapter(basketadapter);
        for(int m=0; m<itemOnCart.size(); m++){
            // Toast.makeText(this.getContext(),itemOnCart.get(m).getItemName() ,Toast.LENGTH_LONG).show();
            Log.i("test",itemOnCart.get(m).getItemName());
            Log.i("test",itemOnCart.get(m).getItemQuantity());
            Log.i("test",itemOnCart.get(m).getItemPrice());

            total=total+ Integer.valueOf(itemOnCart.get(m).getItemPrice());
            }

        tp.setText(String.valueOf(total));

        btnShop = (Button) v.findViewById(R.id.shopping);
        btnCheckout = (Button) v.findViewById(R.id.checkout);

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int position , long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setCancelable(false);
                builder.setTitle("Delete Item");
                builder.setInverseBackgroundForced(true);
                builder.setMessage("Are You Sure To Delete This Item");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                total=total- Integer.valueOf(itemOnCart.get(position).getItemPrice());
                                itemOnCart.remove(position);
                                tp.setText(String.valueOf(total));
                                basketadapter.notifyDataSetChanged();

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
                manager.beginTransaction().replace(R.id.content_layout, menu1Fragment,menu1Fragment.getTag())
                        .addToBackStack(null)
                        .commit();
            }
        });

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add It To Firebase

                FirebaseUser user = nAuth.getCurrentUser();
                DatabaseReference usersRef1 = ref.child("Orders");
                DatabaseReference usersRef2 = usersRef1.child("Order No: "+ ++i);
                DatabaseReference usersRef3 = usersRef2.child("Items");

                DatabaseReference newUserRef2 = usersRef2.push();
                newUserRef2.setValue(user.getUid());


                for(int oi=0; oi<itemOnCart.size(); oi++){
                    Order order = new Order(user.getUid(),itemOnCart.get(oi).getItemName()
                            ,itemOnCart.get(oi).getItemQuantity());
                    DatabaseReference newUserRef3 = usersRef3.push();
                    newUserRef3.setValue(order);

                }




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



}
