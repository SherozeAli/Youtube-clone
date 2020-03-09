package com.example.escomputer.augumentedrealityapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.escomputer.augumentedrealityapp.Activities.MainActivity;
import com.example.escomputer.augumentedrealityapp.Activities.Upload_Actitvity;
import com.example.escomputer.augumentedrealityapp.Activities.UserHome;
import com.example.escomputer.augumentedrealityapp.Adapters.LinkApprovedAdapter;
import com.example.escomputer.augumentedrealityapp.Fragments.Fragment_MyLink;
import com.example.escomputer.augumentedrealityapp.Fragments.Fragment_Upload;
import com.example.escomputer.augumentedrealityapp.ModelClasses.LinkClass;
import com.example.escomputer.augumentedrealityapp.ModelClasses.MyLinkModel;
import com.example.escomputer.augumentedrealityapp.ModelClasses.UserInformation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Category extends AppCompatActivity implements View.OnClickListener{
    private ListView listview;
    private DatabaseReference refer,refer2,refer3,ref;
    private FirebaseAuth fba;
    private ArrayList<String> arraylist=new ArrayList<>();
    ArrayAdapter<String> adaptor;
    UserInformation user;
    LinkClass linkclass;
    private TextView selectedtextview;
    public String val="haah",valTemp;
    private Button category_game,category_other,category_family;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        listview = (ListView) findViewById(R.id.listview);
        category_game=(Button) findViewById(R.id.category_game);
        category_family=(Button) findViewById(R.id.category_family);
        category_other=(Button) findViewById(R.id.category_other);
        selectedtextview=(TextView) findViewById(R.id.selectedtextview);
        user = new UserInformation();
        linkclass = new LinkClass();
        fba = FirebaseAuth.getInstance();
        FirebaseUser firebaseuser = fba.getCurrentUser();
        final String id = firebaseuser.getUid();
        refer = FirebaseDatabase.getInstance().getReference("Category").child("Others");
        refer2 = FirebaseDatabase.getInstance().getReference("Category").child("Games");
        refer3 = FirebaseDatabase.getInstance().getReference("Category").child("Family");

        adaptor = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, arraylist);
        category_game.setOnClickListener(this);
        category_other.setOnClickListener(this);
        category_family.setOnClickListener(this);
        String userName = getIntent().getStringExtra("select");
        selectedtextview.setText("Text: "  + userName);




    }


    @Override
    public void onClick(View view) {
        if(view==category_family){
            refer3.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    arraylist.clear();
                    for(DataSnapshot snap: dataSnapshot.getChildren()) {
                        String as=snap.getKey();

                        arraylist.add(as);
                        adaptor.notifyDataSetChanged();
                    }

                    listview.setAdapter(adaptor);
                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            val= arraylist.get(i);
                            selectedtextview.setText(val);
                            valTemp=selectedtextview.getText().toString();
                            Intent intent = new Intent(Category.this, Upload_Actitvity.class);
                            intent.putExtra("data", val);
                            startActivity(intent);


                        }
                    });

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        if(view==category_game){
            refer2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    arraylist.clear();
                    for(DataSnapshot snap: dataSnapshot.getChildren()) {
                        String as=snap.getKey();

                        arraylist.add(as);
                        adaptor.notifyDataSetChanged();
                    }

                    listview.setAdapter(adaptor);
                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            String val= arraylist.get(i);
                            Toast.makeText(Category.this, val, Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        } if(view==category_other){
            refer.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    arraylist.clear();
                    for(DataSnapshot snap: dataSnapshot.getChildren()) {
                        String as=snap.getKey();
                        if(as.equals("Games") || as.equals("Family")){
                            continue;
                        }
                        arraylist.add(as);
                        adaptor.notifyDataSetChanged();
                    }

                    listview.setAdapter(adaptor);
                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            String val= arraylist.get(i);

                        }
                    });

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }


    }
}
