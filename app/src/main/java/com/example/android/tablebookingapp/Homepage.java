package com.example.android.tablebookingapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Transformation;

public class Homepage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    final DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth nAuth;
    private ProgressDialog nProgressDialog;
    private GoogleApiClient mGoogleApiClient;
    private Uri photoUrl;
    private ImageView img;
    private TextView mName, mEmail;
    private static String UserID;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        nAuth = FirebaseAuth.getInstance();
        nProgressDialog = new ProgressDialog(this);

        Bundle bundle = getIntent().getExtras();

        ///Fetching UserID of the user from login or signup page/////////
       UserID = bundle.getString("UserID");





        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ///For Initial
        Menu1Fragment menu1Fragment = new Menu1Fragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.content_layout, menu1Fragment, menu1Fragment.getTag())
                .addToBackStack(null)
                .commit();
        mEmail = (TextView) navigationView.getHeaderView(0).findViewById(R.id.userEmail);
        img = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.userImage);
        mName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.userName);
        getCurrentinfo();



        mGoogleApiClient = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    } ///OnCreate Endd


    @Override
    public void onBackPressed() {



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

            /*if(getFragmentManager().getBackStackEntryCount() > 0){
                getFragmentManager().popBackStackImmediate();
            }
            else{
                super.onBackPressed();
            }*/

        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

            Menu1Fragment menu1Fragment = new Menu1Fragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_layout,menu1Fragment,menu1Fragment.getTag())
                    .addToBackStack(null)
                    .commit();
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            SeatFragment seatFragment = new SeatFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_layout, seatFragment , seatFragment.getTag())
                    .addToBackStack(null)
                    .commit();


        } else if (id == R.id.nav_slideshow) {

            GMapFragment gMapFragment = new GMapFragment();

            //for Passing userid to other fragments within same activity
            Bundle bundle = new Bundle();
            bundle.putString("UsedID",UserID );
            gMapFragment.setArguments(bundle);

            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_layout, gMapFragment, gMapFragment.getTag())
                    .addToBackStack(null)
                    .commit();

        } else if (id == R.id.nav_share) {
            SettingFragment settingFragment = new SettingFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_layout, settingFragment, settingFragment.getTag())
                    .addToBackStack(null)
                    .commit();

        } else if (id == R.id.nav_logout) {
            nProgressDialog.setMessage("Signing Out");
	        nAuth.signOut();
            mGoogleApiClient.disconnect();
            nProgressDialog.show();
            Intent i = new Intent(Homepage.this, MainActivity.class);
            startActivity(i);
            
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onStart() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mGoogleApiClient.connect();
        super.onStart();
    }



   private void getCurrentinfo()
   {
        nAuth = FirebaseAuth.getInstance();
        FirebaseUser user = nAuth.getCurrentUser();
        DatabaseReference usersRef = ref.child("Users");
        usersRef.orderByChild("userId").equalTo(user.getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Profile newUser = dataSnapshot.getValue(Profile.class);
                mName.setText(newUser.uname);
                mEmail.setText(newUser.email);

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


           /*     Picasso.with(getApplicationContext())
                        .load(photoUrl.toString())
                        .transform(new CircleTransform())
                        .placeholder(R.drawable.logo)
                        .resize(120, 120)
                        .centerCrop()
                        .into(img);*/


            }


    @Override
    public void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    public class CircleTransform implements Transformation {
        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());

            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
            if (squaredBitmap != source) {
                source.recycle();
            }

            Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            BitmapShader shader = new BitmapShader(squaredBitmap,
                    BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
            paint.setShader(shader);
            paint.setAntiAlias(true);

            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);

            squaredBitmap.recycle();
            return bitmap;
        }

        @Override
        public String key() {
            return "circle";
        }
    }


}
