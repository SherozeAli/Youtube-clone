package com.example.escomputer.augumentedrealityapp.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.escomputer.augumentedrealityapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyFavouriteActivity2 extends AppCompatActivity {
private TextView link;
private ImageView fav;
    private DatabaseReference refer,refer2,refer3;
    private FirebaseAuth fba;
    private FirebaseUser user;
    int counter;
    String bool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favourite2);
        fba= FirebaseAuth.getInstance();
        user=fba.getCurrentUser();
        link=(TextView) findViewById(R.id.link);
        link.setText(MyFavouritesActivity.link+"");
        counter= Integer.parseInt(MyFavouritesActivity.linkcounter);
        refer2= FirebaseDatabase.getInstance().getReference("Favourites").child(user.getUid()).child("Link "+counter);
        refer2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    bool = dataSnapshot.getValue().toString();
                    if (bool.equals("TRUE")) {
                        Toast.makeText(MyFavouriteActivity2.this, "if start", Toast.LENGTH_SHORT).show();

                        fav.setImageResource(R.drawable.favheart);

                    }
                     if(bool.equals("FALSE")){
                        Toast.makeText(MyFavouriteActivity2.this, "else if start", Toast.LENGTH_SHORT).show();

                        fav.setImageResource(R.drawable.favorite);

                    }


                }catch (Exception e){
                    Toast.makeText(MyFavouriteActivity2.this, "E.getMessage : Not Registeredd", Toast.LENGTH_SHORT).show();
                    refer2.setValue("FALSE");
                    fav.setImageResource(R.drawable.favorite);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        fav=(ImageView)findViewById(R.id.favourite);
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manageFavourite();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    private void manageFavourite(){

        if (bool.equals("TRUE")) {
            Toast.makeText(MyFavouriteActivity2.this, "if manage", Toast.LENGTH_SHORT).show();

            refer2.setValue("FALSE");
            fav.setImageResource(R.drawable.favorite);


        }
        else if(bool.equals("FALSE")){
            Toast.makeText(MyFavouriteActivity2.this, "elseif 2 manage", Toast.LENGTH_SHORT).show();

            fav.setImageResource(R.drawable.favheart);
            refer2.setValue("TRUE");


        }
        else {

            refer2.setValue("TRUE");

            fav.setImageResource(R.drawable.favheart);
        }


    }
}
