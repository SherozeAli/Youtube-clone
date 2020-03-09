package com.example.escomputer.augumentedrealityapp.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.escomputer.augumentedrealityapp.ModelClasses.LinkClass;
import com.example.escomputer.augumentedrealityapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView textview;
    private Button logout,uploadlink;
    private FirebaseAuth fba;
    private EditText linkfield;
    private DatabaseReference refer;
    private DatabaseReference refer2;
    private static int x,y;
    private  Map<String, Object> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        logout=(Button) findViewById(R.id.profile_btn);
        textview=(TextView) findViewById(R.id.textview);
        linkfield=(EditText) findViewById(R.id.linkfield);
        uploadlink=(Button) findViewById(R.id.uploadlink);

        logout.setOnClickListener(this);
        textview.setOnClickListener(this);
        uploadlink.setOnClickListener(this);


        fba=FirebaseAuth.getInstance();
        refer= FirebaseDatabase.getInstance().getReference("Links");
        refer2= FirebaseDatabase.getInstance().getReference("Counter");

        if(fba.getCurrentUser()==null){
            Intent i=new Intent(ProfileActivity.this,LoginActivity.class);
            startActivity(i);
            finish();
        }

        FirebaseUser fbu=fba.getCurrentUser();
        textview.setText("Welcome"+" " + fbu.getEmail());
    }

    @Override
    public void onClick(View view) {
        if(view == logout){

            fba.signOut();
            Intent i=new Intent(ProfileActivity.this,LoginActivity.class);
            startActivity(i);
            finish();
        }
        if(view == uploadlink){

            saveLink();
        }
    }

private void saveLink(){



    String link=linkfield.getText().toString();
    FirebaseUser  firebaseuser =fba.getCurrentUser();
    //LinkClass linkclass=new LinkClass(link,firebaseuser.getUid());


   // refer.child("Link "+ (y=x+1)).setValue(linkclass);
    String num=String.valueOf(y);
    refer2.child("number").setValue(num);
    Toast.makeText(ProfileActivity.this, "I'm number "+num, Toast.LENGTH_SHORT).show();
    Toast.makeText(ProfileActivity.this, "Succesfully Uploaded", Toast.LENGTH_SHORT).show();
    FirebaseUser user=fba.getCurrentUser();

    refer2.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            try{
                map = (Map<String, Object>) dataSnapshot.getValue();
                x=Integer.parseInt(map.get("number").toString());
                Toast.makeText(ProfileActivity.this, "im X "+String.valueOf(x), Toast.LENGTH_SHORT).show();

            }catch (Exception e) {
                Toast.makeText(ProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }}
        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });


}

    @Override
    protected void onStart() {
        super.onStart();

        refer2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try{
                    map = (Map<String, Object>) dataSnapshot.getValue();
                    x=Integer.parseInt(map.get("number").toString());
                    Toast.makeText(ProfileActivity.this, "im X "+String.valueOf(x), Toast.LENGTH_SHORT).show();

                }catch (Exception e) {
                    Toast.makeText(ProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }}
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
