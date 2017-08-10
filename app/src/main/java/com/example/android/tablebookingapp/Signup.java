package com.example.android.tablebookingapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {
    private TextView nSignUpBtn; //  For Firebase Register
    private EditText nEmailField; //  For Firebase Register
    private EditText nPassField; //  For Firebase Register
    private EditText nNameField; //  For Firebase Register
    private EditText nCountryField; //  For Firebase Register
    private EditText nCityField; //  For Firebase Register
    private EditText nPhoneField; //  For Firebase Register
    private FirebaseAuth nAuth;
    private ProgressDialog nProgressDialog;
    private TextView nSignInBtn;
    final DatabaseReference ref = FirebaseDatabase.getInstance().getReference();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);






        nSignUpBtn = (TextView) findViewById(R.id.signup);
        nSignInBtn = (TextView) findViewById(R.id.signIn);
        nEmailField = (EditText) findViewById(R.id.editText_email);
        nPassField = (EditText) findViewById(R.id.editText_password);
        nNameField = (EditText) findViewById(R.id.editText_name);
        nPhoneField = (EditText) findViewById(R.id.editText_phone);
        nCountryField = (EditText) findViewById(R.id.editText_country);
        nCityField = (EditText) findViewById(R.id.editText_city);



        nAuth = FirebaseAuth.getInstance();
        nProgressDialog = new ProgressDialog(this);


        nSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signup.this, MainActivity.class));
            }
        });


        nSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(nEmailField.getText().toString()) || !TextUtils.isEmpty(nPassField.getText().toString())
                        || !TextUtils.isEmpty(nPhoneField.getText().toString()) || !TextUtils.isEmpty(nNameField.getText().toString())
                        || !TextUtils.isEmpty(nCountryField.getText().toString())|| !TextUtils.isEmpty(nCityField.getText().toString())) {
                    nProgressDialog.setMessage("Registering User");
                    nProgressDialog.show();
                    startSignUp();
                } else{
                    Toast.makeText(Signup.this, "Fields Are Empty", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    private void startSignUp() {
        final String signupemail = nEmailField.getText().toString();
        final String signuppass = nPassField.getText().toString();
        final String signupname = nNameField.getText().toString();
        final String signupphone = nPhoneField.getText().toString();
        final String signupcountry = nCountryField.getText().toString();
        final String signupcity = nCityField.getText().toString();



        nAuth.createUserWithEmailAndPassword(signupemail, signuppass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(Signup.this, "Couldn't Register", Toast.LENGTH_LONG).show();
                    nProgressDialog.dismiss();

                }else{
                    FirebaseUser user = nAuth.getCurrentUser();
                    Profile muser = new Profile(user.getUid(), signupname,signupemail,signupphone,signupcountry,signupcity);
                    DatabaseReference usersRef = ref.child("Users");
                    DatabaseReference newUserRef = usersRef.push();

                    // Get the unique ID generated by a push()
                    String ProfileId = newUserRef.getKey();
                    newUserRef.setValue(muser);
                    Intent i=new Intent(Signup.this, Homepage.class);
                    i.putExtra("UserID",ProfileId);
                    startActivity(i);
                   nProgressDialog.dismiss();



                }
            }
        });
    } // Firebase reg Close



}
