package com.example.escomputer.augumentedrealityapp.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.escomputer.augumentedrealityapp.Adapters.AdminApprovedLinkAdapter;
import com.example.escomputer.augumentedrealityapp.Adapters.LinkApprovedAdapter;
import com.example.escomputer.augumentedrealityapp.ModelClasses.AdminApprovedModel;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragmen_Admin extends Fragment {
    private ListView listview;
    private DatabaseReference refer,refer2,refer3;
    private FirebaseAuth fba;
    private ArrayList<AdminApprovedModel> arraylist=new ArrayList<>();
    AdminApprovedLinkAdapter adaptor;
    UserInformation user;
    LinkClass linkclass;
    AdminApprovedModel adminApprovedModel;

    public Fragmen_Admin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_fragmen__admin, container, false);

       try {
           listview = (ListView) view.findViewById(R.id.listview);
           user = new UserInformation();
           linkclass = new LinkClass();


           fba = FirebaseAuth.getInstance();
           FirebaseUser firebaseuser = fba.getCurrentUser();
           final String id = firebaseuser.getUid();

           refer2 = FirebaseDatabase.getInstance().getReference("Links");
           refer2.addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   for (DataSnapshot snap : dataSnapshot.getChildren()) {
                       linkclass = snap.getValue(LinkClass.class);

                       String title = linkclass.getUser_title();
                       String descr = linkclass.getUser_description();
                       String link = linkclass.getLink();
                       String img = linkclass.getUser_image();
                       String userid = linkclass.getUser_id();
                       String counter = linkclass.getLinkCounter();
                       String status = linkclass.getApproved_status();

                       adminApprovedModel = new AdminApprovedModel(userid, link, title, descr, img, status, counter);
                       arraylist.add(adminApprovedModel);

                   }
                   adaptor = new AdminApprovedLinkAdapter(getActivity(), arraylist);

                   listview.setAdapter(adaptor);


               }

               @Override
               public void onCancelled(@NonNull DatabaseError databaseError) {

               }
           });

       }
       catch (Exception g)
       {
           Log.d("masla ", String.valueOf(g));

           Toast.makeText(getActivity(), "masla "+g, Toast.LENGTH_SHORT).show();

       }
        return view;
    }

}
