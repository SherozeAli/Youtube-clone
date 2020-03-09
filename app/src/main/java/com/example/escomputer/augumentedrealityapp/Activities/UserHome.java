package com.example.escomputer.augumentedrealityapp.Activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.escomputer.augumentedrealityapp.Adapters.LinkApprovedAdapter;
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

public class UserHome extends AppCompatActivity {


    private ListView listview;
    private DatabaseReference refer,refer2,refer3;
    private FirebaseAuth fba;
    private ArrayList<MyLinkModel> arraylist=new ArrayList<>();
    LinkApprovedAdapter adaptor;
    Button search;
    UserInformation user;
    LinkClass linkclass;
    TextView searchbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);



        search=(Button)findViewById(R.id.searchbtn);
        searchbar=(TextView)findViewById(R.id.searcbar);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String mytext=searchbar.getText()+"";

                refer2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        arraylist.clear();

                        for(DataSnapshot snap: dataSnapshot.getChildren()) {
                            linkclass = snap.getValue(LinkClass.class);

                            String title = linkclass.getUser_title();
                            String descr = linkclass.getUser_description();
                            String link = linkclass.getLink();
                            String img = linkclass.getUser_image();
                            String userid = linkclass.getUser_id();
                            String counter = linkclass.getLinkCounter();
                            String status = linkclass.getApproved_status();
                            String feature_stauts=linkclass.getFeature_status();
                            String cat=linkclass.getCategory();


                            MyLinkModel myLinkModel=new MyLinkModel(userid, link, title, descr, img, status, counter,feature_stauts,cat);

                            String st=myLinkModel.getTitle()+"";

                            if(st.contains(""+mytext)) {
                                arraylist.add(myLinkModel);
                            }

                        }
                        adaptor = new LinkApprovedAdapter(UserHome.this, arraylist);

                        listview.setAdapter(adaptor);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });




            }
        });

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
                for(DataSnapshot snap: dataSnapshot.getChildren()) {
                    linkclass = snap.getValue(LinkClass.class);

                    String title = linkclass.getUser_title();
                    String descr = linkclass.getUser_description();
                    String link = linkclass.getLink();
                    String img = linkclass.getUser_image();
                    String userid = linkclass.getUser_id();
                    String counter = linkclass.getLinkCounter();
                    String status = linkclass.getApproved_status();
                    String feature_stauts=linkclass.getFeature_status();
                    String cat=linkclass.getCategory();


                    MyLinkModel myLinkModel=new MyLinkModel(userid, link, title, descr, img, status, counter,feature_stauts,cat);

                    String st=myLinkModel.getStatus()+"";

                if(st.equals("TRUE")) {
                    arraylist.add(myLinkModel);
                }

                }
                adaptor = new LinkApprovedAdapter(UserHome.this, arraylist);

                listview.setAdapter(adaptor);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
}
