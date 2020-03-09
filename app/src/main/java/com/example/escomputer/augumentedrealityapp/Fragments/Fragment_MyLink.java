package com.example.escomputer.augumentedrealityapp.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.escomputer.augumentedrealityapp.Adapters.LinkApprovedAdapter;
import com.example.escomputer.augumentedrealityapp.ModelClasses.LinkClass;
import com.example.escomputer.augumentedrealityapp.ModelClasses.MyLinkModel;
import com.example.escomputer.augumentedrealityapp.R;
import com.example.escomputer.augumentedrealityapp.ModelClasses.UserInformation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_MyLink extends Fragment {
    private ListView listview;
    private DatabaseReference refer,refer2,refer3;
    private FirebaseAuth fba;
    private ArrayList<MyLinkModel> arraylist=new ArrayList<>();
  LinkApprovedAdapter adaptor;
    UserInformation user;
    LinkClass linkclass;
    int kk=0;


    public Fragment_MyLink() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=inflater.inflate(R.layout.fragment_fragment__my_link, container, false);
       listview=(ListView) view.findViewById(R.id.listview);
       user=new UserInformation();
       linkclass=new LinkClass();


       fba=FirebaseAuth.getInstance();
        FirebaseUser firebaseuser = fba.getCurrentUser();
        final String id=firebaseuser.getUid();

        refer2= FirebaseDatabase.getInstance().getReference("Favourites").child(id);
        // refer= FirebaseDatabase.getInstance().getReference("User");
        // DatabaseReference ref=refer.child(id);


        refer2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snap: dataSnapshot.getChildren()){
                    String str=snap.getValue(String.class);

                    if(str.equals("TRUE")){
                        final String key=snap.getKey();
                        Toast.makeText(getContext(), "k"+key, Toast.LENGTH_SHORT).show();

                        refer3= FirebaseDatabase.getInstance().getReference("Links");




                        refer3.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot2) {
                                for(DataSnapshot ds:dataSnapshot2.getChildren()){

                                    String sttt  =ds.getKey();

                                    if(sttt.equals(key)) {
                                     //   Toast.makeText(getActivity(), "dsss" + ds, Toast.LENGTH_SHORT).show();

                                        LinkClass linkclass2=ds.getValue(LinkClass.class);

                                        String t= linkclass2.getUser_title();
                                         String c=linkclass2.getCategory();
                                        String des=linkclass2.getUser_description();
                                        Toast.makeText(getContext(), "title " + t + " category  " + c + " des "+des, Toast.LENGTH_SHORT).show();
                                        //kk++;
                                    }

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });



                    }


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }

}
