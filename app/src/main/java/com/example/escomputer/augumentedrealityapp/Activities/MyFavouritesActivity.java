package com.example.escomputer.augumentedrealityapp.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.escomputer.augumentedrealityapp.Adapters.LinkApprovedAdapter;
import com.example.escomputer.augumentedrealityapp.Adapters.MyfavouriteAdaptor;
import com.example.escomputer.augumentedrealityapp.ModelClasses.FavouriteModel;
import com.example.escomputer.augumentedrealityapp.ModelClasses.LinkClass;
import com.example.escomputer.augumentedrealityapp.ModelClasses.MyLinkModel;
import com.example.escomputer.augumentedrealityapp.ModelClasses.UserInformation;
import com.example.escomputer.augumentedrealityapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyFavouritesActivity extends AppCompatActivity {
    private ListView listview;
    private DatabaseReference refer,refer2,refer3;
    private FirebaseAuth fba;
    private ArrayList<FavouriteModel> arraylist=new ArrayList<>();
    MyfavouriteAdaptor adaptor;
    UserInformation user;
    LinkClass linkclass;
    public static String linkcounter,link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favourites);

        listview = (ListView) findViewById(R.id.listview);
        user = new UserInformation();
        linkclass = new LinkClass();
        fba = FirebaseAuth.getInstance();
        FirebaseUser firebaseuser = fba.getCurrentUser();
        final String id = firebaseuser.getUid();
        refer2 = FirebaseDatabase.getInstance().getReference("Links");
        refer2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arraylist.clear();
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    linkclass = snap.getValue(LinkClass.class);
                    String title = linkclass.getUser_title();
                    String descr = linkclass.getUser_description();
                    String link = linkclass.getLink();
                    String img = linkclass.getUser_image();
                    String userid = linkclass.getUser_id();
                    String counter = linkclass.getLinkCounter();
                    String status = linkclass.getApproved_status();
                    String feature_status = linkclass.getFeature_status();
                    String category = linkclass.getCategory();
                    FavouriteModel myFavModel = new FavouriteModel(userid,status, title,  descr,link,category, img , counter);
                           if(status.equals("TRUE")) {
                               arraylist.add(myFavModel);
                           }
                }

                adaptor = new MyfavouriteAdaptor(MyFavouritesActivity.this, arraylist);
                listview.setAdapter(adaptor);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                linkcounter=arraylist.get(i).getLinkcounter();
                link=arraylist.get(i).getLink();
                Intent in=new Intent(MyFavouritesActivity.this,MyFavouriteActivity2.class);
                startActivity(in);
            }
        });
    }}