package com.example.escomputer.augumentedrealityapp.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Base64;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.escomputer.augumentedrealityapp.Fragments.Fragmen_Admin;
import com.example.escomputer.augumentedrealityapp.Fragments.Fragment_Home;
import com.example.escomputer.augumentedrealityapp.Fragments.Fragment_MyLink;
import com.example.escomputer.augumentedrealityapp.Fragments.Fragment_Upload;
import com.example.escomputer.augumentedrealityapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TextView textview;
    private ImageView imageview;
    private String imageString;
   private int PICK_IMAGE_REQUEST=234;
    private Button upload;
    DatabaseReference refer,refer2;
    private FirebaseAuth fba;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
      try {
          super.onCreate(savedInstanceState);

          setContentView(R.layout.activity_main);
          Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
          setSupportActionBar(toolbar);
          FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
          fab.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                          .setAction("Action", null).show();
              }
          });

          DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
          ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                  this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
          drawer.addDrawerListener(toggle);
          toggle.syncState();

          NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
          View header = navigationView.getHeaderView(0);

          imageview = header.findViewById(R.id.imageview4);
          textview = header.findViewById(R.id.textview4);
          upload = header.findViewById(R.id.upload_button);
          upload.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                   showFileChooser();
              }
          });

          navigationView.setNavigationItemSelectedListener(this);






      }
      catch (Exception e){
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
    } }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "ahhaahahahahah", Toast.LENGTH_SHORT).show();
        fba=FirebaseAuth.getInstance();
        FirebaseUser firebaseuser = fba.getCurrentUser();
        DatabaseReference refer1 = FirebaseDatabase.getInstance().getReference("User");

        String id=firebaseuser.getUid();

            DatabaseReference refer2 = refer1.child(id);


            DatabaseReference refer3 = refer2.child("profilepic");

            refer3.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String temp = dataSnapshot.getValue().toString();

                    if (temp.equals("notgiven")) {
                        Toast.makeText(MainActivity.this, "nullingg", Toast.LENGTH_SHORT).show();
                        imageview.setImageResource(R.drawable.usericon);
                    } else {

                        byte[] decodedString = Base64.decode(temp+"", Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);


                        imageview.setImageBitmap(decodedByte);

                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        DatabaseReference refer4 = refer2.child("name");
        refer4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.getValue().toString();
                Toast.makeText(MainActivity.this,"Main USER :"+ name, Toast.LENGTH_SHORT).show();
            textview.setText(name);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Toast.makeText(this, "Home Open", Toast.LENGTH_SHORT).show();
            FragmentManager manager =getSupportFragmentManager();
            FragmentTransaction transaction =manager.beginTransaction();
            transaction.replace(R.id.main_layout,new Fragment_Home());
            transaction.addToBackStack(null);
            transaction.commit();


              }
        else if (id == R.id.nav_upload) {
            Toast.makeText(this, "Upload a link", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainActivity.this, Upload_Actitvity.class);
            startActivity(i);




        }
        else if (id == R.id.nav_mylink) {
            Toast.makeText(this, "My links", Toast.LENGTH_SHORT).show();
            FragmentManager manager =getSupportFragmentManager();
            FragmentTransaction transaction =manager.beginTransaction();
            transaction.replace(R.id.main_layout,new Fragment_MyLink());
            transaction.addToBackStack(null);
            transaction.commit();



        }  else if (id == R.id.nav_myfav) {
            Toast.makeText(this, "My links", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainActivity.this, MyFavouritesActivity.class);
            startActivity(i);
            finish();



        }else if (id == R.id.nav_signout) {
            fba.signOut();
            Intent i = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(i);
            finish();


        } else if (id == R.id.nav_aboutus) {
            Intent in=new Intent(MainActivity.this,UserHome.class);
            startActivity(in);


        } else if (id == R.id.nav_Term) {
            Intent in=new Intent(MainActivity.this,FeaturedVideos.class);
            startActivity(in);

        }
        else if (id == R.id.nav_recently) {

        }
        else if (id == R.id.nav_admin) {
           Intent in=new Intent(MainActivity.this,Admin_Activity.class);
           startActivity(in);

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void uploadFragment(Fragment f){
        Toast.makeText(this, "Upload a Link", Toast.LENGTH_SHORT).show();
        FragmentManager manager =getSupportFragmentManager();
        FragmentTransaction transaction =manager.beginTransaction();
        transaction.replace(R.id.main_layout,f);
        transaction.addToBackStack(null);
        transaction.commit();

    }
    private void myLinks(Fragment f2){

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Toast.makeText(this, "Main wala protected", Toast.LENGTH_SHORT).show();
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filepath2 = data.getData();
            imageString = filepath2.toString();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath2);
                Bitmap scaledBitmap = scaleDown(bitmap, 200, true);
                imageString = imagestorageindatabase(scaledBitmap);
                Toast.makeText(this, "MAIN"+imageString, Toast.LENGTH_SHORT).show();
                imageview.setImageResource(0);
                Bitmap myBitmap = convertbase64intobitmap(imageString);
                imageview.setImageBitmap(myBitmap);


                fba=FirebaseAuth.getInstance();
                FirebaseUser firebaseuser = fba.getCurrentUser();
                String id=firebaseuser.getUid();
                refer= FirebaseDatabase.getInstance().getReference("User");
                refer.child(id).child("profilepic").setValue(imageString);
                Toast.makeText(MainActivity.this,"MAIN ID :"+ id, Toast.LENGTH_SHORT).show();




            } catch (IOException e) {
                e.printStackTrace();
            }


        } else {
            Toast.makeText(this, "MAIN KA MASLA", Toast.LENGTH_SHORT).show();
        }

    }


    Bitmap convertbase64intobitmap(String imgString2) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] imageBytes = baos.toByteArray();
        imageBytes = Base64.decode(imgString2, Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        return decodedImage;
    }


    public String imagestorageindatabase(Bitmap bitmap) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteFormat = stream.toByteArray();
        String encodedImage = Base64.encodeToString(byteFormat, Base64.NO_WRAP);
        return encodedImage;


    }


    private void showFileChooser() {
        Intent it = new Intent();
        it.setType("image/*");
        it.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(it, "Select an image"), PICK_IMAGE_REQUEST);
    }


    public static Bitmap scaleDown(Bitmap realImage, float maxImageSize,
                                   boolean filter) {
        float ratio = Math.min(
                (float) maxImageSize / realImage.getWidth(),
                (float) maxImageSize / realImage.getHeight());
        int width = Math.round((float) ratio * realImage.getWidth());
        int height = Math.round((float) ratio * realImage.getHeight());

        Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, width,
                height, filter);
        return newBitmap;
    }




}
