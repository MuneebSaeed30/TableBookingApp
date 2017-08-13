package com.example.android.tablebookingapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class SeatFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Menu> menu= new ArrayList<>();
    int minteger = 0;



    public SeatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seat, container, false);

        return view;
    }

}




