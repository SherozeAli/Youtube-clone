package com.example.escomputer.augumentedrealityapp.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

import com.example.escomputer.augumentedrealityapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Home extends Fragment implements View.OnClickListener{
    private EditText search_editText;
    private Button search_button;
    private ListView listview;
    private DatabaseReference refer,refer2,refer3;
    private FirebaseAuth fba;
    private ArrayList<String> arraylist=new ArrayList<>();
    ArrayAdapter<String> adaptor;
    UserInformation user;
    LinkClass linkclass;
    int count;
    AdminApprovedModel adminApprovedModel;
    public Fragment_Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_fragment__home, container, false);
        listview=(ListView) view.findViewById(R.id.home_listview);
        search_button=(Button) view.findViewById(R.id.search_button);
        search_editText=(EditText) view.findViewById(R.id.search_edittext);
        search_button.setOnClickListener(this);
        adaptor = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, arraylist);

        user=new UserInformation();
        linkclass=new LinkClass();
       // count=Intlinkclass.getLinkCounter();

        fba=FirebaseAuth.getInstance();
        FirebaseUser firebaseuser = fba.getCurrentUser();
        final String id=firebaseuser.getUid();

        refer2= FirebaseDatabase.getInstance().getReference("Links");

        return view;
    }


    @Override
    public void onClick(View view) {
        if(view == search_button){
            searchStuffs();


        }
    }

    private void searchStuffs() {
final String search_string=search_editText.getText().toString();

        refer2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snap: dataSnapshot.getChildren()) {
                    linkclass = snap.getValue(LinkClass.class);


                    String title = linkclass.getUser_title();

                   if(title.equals(search_string)) {
                       arraylist.add(title);
                   }
                }


                listview.setAdapter(adaptor);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
