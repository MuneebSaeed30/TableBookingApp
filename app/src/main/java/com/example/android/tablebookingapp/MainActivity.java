package com.example.android.tablebookingapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity
    {


    private TextView nsigninBtn ; //  For Firebase Login
    private EditText nEmailField; //  For Firebase Login
    private EditText nPassField; //  For Firebase Login
    private FirebaseAuth nAuth;
    private  FirebaseAuth.AuthStateListener nAuthListener;
    private ProgressDialog nProgressDialog;
    private TextView nSignupBtn; // for firebase signup


    //////for Google Signin

    private TextView nSignIn;
    private static final int RC_SIGN_IN = 1;
    private GoogleApiClient mGoogleApiClient;
    private static final String TAG ="MAIN_ACTIVITY";
        private String nPhone ;
        private String UserID;


        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference();





        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Firebase.setAndroidContext(this);


            ///Fire base Sign in////////////////////////////////////////////////////////////////////////////////////////

            nsigninBtn = (TextView) findViewById(R.id.signin);
            nPassField = (EditText) findViewById(R.id.pass);
            nEmailField = (EditText) findViewById(R.id.email);
            nSignupBtn = (TextView) findViewById(R.id.signUp);

            nAuth = FirebaseAuth.getInstance();
            nProgressDialog = new ProgressDialog(this);


            nsigninBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (TextUtils.isEmpty(nEmailField.getText().toString()) ||
                            TextUtils.isEmpty(nPassField.getText().toString())) {
                        Toast.makeText(MainActivity.this, "Fields Are Empty", Toast.LENGTH_LONG).show();
                    } else {
                        nProgressDialog.setMessage("Signing In");
                        nProgressDialog.show();
                        startSignIn();
                    }
                }
            });

            //firebase sign up//////////////////////////////////////////////////////////////////////////////////////////////////

            nSignupBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, Signup.class));
                }
            });


            //////GoogleSigninnnnn/////////////////////////////////////////////////////////////////////////////////////////////

            nSignIn = (TextView) findViewById(R.id.btngmail);

            // Configure Google Sign In
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build();
            // ATTENTION: This "addApi(AppIndex.API)"was auto-generated to implement the App Indexing API.
            // See https://g.co/AppIndexing/AndroidStudio for more information.
            mGoogleApiClient = new GoogleApiClient.Builder(MainActivity.this)
                    .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                        @Override
                        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                            Toast.makeText(MainActivity.this, "You Got An Error", Toast.LENGTH_LONG).show();

                        }
                    }).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();

            nSignIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    nProgressDialog.setMessage("Signing In");
                    nProgressDialog.show();
                    signIn();


                   /* AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                    View mView = getLayoutInflater().inflate(R.layout.layout_contactdialog, null);
                    final EditText mMoblie = (EditText) mView.findViewById(R.id.contact);
                    Button mOk = (Button) mView.findViewById(R.id.btnOk);
                    mBuilder.setView(mView);
                    final AlertDialog dialog = mBuilder.create();
                    dialog.show();
                    mOk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(!mMoblie.getText().toString().isEmpty()){
                               TextView mPhone=(TextView) findViewById(R.id.Phone);
                                final EditText mMoblie = (EditText) findViewById(R.id.contact);
                                mPhone.setText(mMoblie.getText().toString());


                                signIn();

                                dialog.dismiss();
                            }else{
                                Toast.makeText(MainActivity.this,
                                        "Please Enter Mobile No",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
*/
                    //contactDialog();


                }
            });
        }


                    ////On Create End







            @Override
            protected void onStart() {
                super.onStart();
                mGoogleApiClient.connect();

                FirebaseUser user = nAuth.getCurrentUser();
                if (user != null) {
                    passSignedUserKeyWithIntent();

                }
            }

        private void passSignedUserKeyWithIntent()
        {
            FirebaseUser user = nAuth.getCurrentUser();
            DatabaseReference usersRef = ref.child("Users");
            Query queryRef = usersRef.orderByChild("userId").equalTo(user.getUid());
            queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot child: dataSnapshot.getChildren()) {
                        UserID= child.getKey();
                        Intent i = new Intent(MainActivity.this,Homepage.class);
                        i.putExtra("UserID",UserID);
                        startActivity(i);
                        nProgressDialog.dismiss();
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        @Override
        protected void onStop() {
            mGoogleApiClient.disconnect();
            super.onStop();
        }

        //  For Firebase Login ///////////////////////////////////////////
         private void startSignIn()
        {
        String fireemail = nEmailField.getText().toString();
        String firepass = nPassField.getText().toString();

            nAuth.signInWithEmailAndPassword(fireemail, firepass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Sign In Problem", Toast.LENGTH_LONG).show();
                        nProgressDialog.dismiss();

                    } else{
                        passSignedUserKeyWithIntent();
                    }
                }
            });
        }
        // Firebase Login Close//////////////////////////////////////////////////

        ////GMAILLL Start/////////////////////////////////////////////////


        private void signIn() {
            Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
            startActivityForResult(signInIntent, RC_SIGN_IN);
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data)
        {
            super.onActivityResult(requestCode, resultCode, data);

            // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);

            if (requestCode == RC_SIGN_IN) {
                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                if (result.isSuccess()) {
                    // Google Sign In was successful, authenticate with Firebase
                    GoogleSignInAccount account = result.getSignInAccount();
                    firebaseAuthWithGoogle(account);
                } else {
                    // Google Sign In failed, update UI appropriately
                    // ...
                }
            }
        }


        private void firebaseAuthWithGoogle(GoogleSignInAccount account)
        {
            AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
            nAuth.signInWithCredential(credential)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Log.w(TAG, "signInWithCredential", task.getException());
                                Toast.makeText(MainActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                            // ...
                        }
                    });
        }

        ////gmail close///////////////////////





    }
