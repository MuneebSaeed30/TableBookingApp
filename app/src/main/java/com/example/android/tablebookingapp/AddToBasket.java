package com.example.android.tablebookingapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddToBasket extends Fragment {

    private TextView mesg;
    private Button checkout;


    public AddToBasket() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View v=inflater.inflate(R.layout.fragment_add_to_basket, container, false);

        mesg= (TextView) v.findViewById(R.id.msg);
        checkout= (Button) v.findViewById(R.id.checkout);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mesg.setText("Checked Out");
            }
        });



        return v;
    }

}
