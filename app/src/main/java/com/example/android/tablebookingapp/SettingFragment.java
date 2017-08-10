package com.example.android.tablebookingapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SettingFragment extends Fragment {

    final DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth nAuth;
    private TextView mUserName, mEmail,mPhone, mCountry,mCity;

    public SettingFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_setting, container, false);


        mUserName=(TextView) v.findViewById(R.id.UserName);
        mEmail=(TextView) v.findViewById(R.id.UserEmail);
        mPhone=(TextView) v.findViewById(R.id.UserContact);
        mCountry=(TextView) v.findViewById(R.id.UserCountry);
        mCity=(TextView) v.findViewById(R.id.UserCity);
/*
        mEmail.setEnabled(false);
        mCountry.setEnabled(false);
        mCity.setEnabled(false);*/
        nAuth = FirebaseAuth.getInstance();

        FirebaseUser user = nAuth.getCurrentUser();
        DatabaseReference usersRef = ref.child("Users");
        usersRef.orderByChild("userId").equalTo(user.getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Profile newUser = dataSnapshot.getValue(Profile.class);
                mUserName.setText(newUser.uname);
                mEmail.setText(newUser.email);
                mPhone.setText(newUser.contact);
                mCity.setText(newUser.city);
                mCountry.setText(newUser.country);
                


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        return v;
    }

}
