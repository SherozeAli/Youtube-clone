package com.example.escomputer.augumentedrealityapp.Adapters;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.escomputer.augumentedrealityapp.Activities.MainActivity;
import com.example.escomputer.augumentedrealityapp.Activities.MainActivity2;
import com.example.escomputer.augumentedrealityapp.Activities.RegistrationActivity;
import com.example.escomputer.augumentedrealityapp.ModelClasses.AdminApprovedModel;
import com.example.escomputer.augumentedrealityapp.ModelClasses.LinkClass;
import com.example.escomputer.augumentedrealityapp.ModelClasses.UserInformation;
import com.example.escomputer.augumentedrealityapp.R;
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdminApprovedLinkAdapter extends ArrayAdapter  {
    Context context;
    List<AdminApprovedModel> adminApprovedModelList = new ArrayList<AdminApprovedModel>();
    private Button approve,play,delete;
    private  TextView tvTitle,tvDescription,tvlink,tvUserID;
    private ImageView tvImage;
    private DatabaseReference refer,refer2,refer3,refer4;
    private FirebaseAuth fba;
    private String title,description,image,link,userID;
    AdminApprovedModel adminApprovedModel;
    int p;
    private static int x,y;
    private Map<String, Object> map;
    private ImageView approved_icon;
    private String approve_status,counter,feature_status;

    public AdminApprovedLinkAdapter(@NonNull Context context, List<AdminApprovedModel> adminApprovedModelList) {
        super(context, R.layout.admin_approval_layout, adminApprovedModelList);
        this.context = context;
        this.adminApprovedModelList=adminApprovedModelList;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {



        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        View view = inflater.inflate(R.layout.admin_approval_layout, parent, false);

        try {
            adminApprovedModel = adminApprovedModelList.get(position);
            // Log.d("adapterArrayList : ",adminApprovedModelList.size()+"");

            approved_icon = (ImageView) view.findViewById(R.id.approved_icon);
            tvTitle = (TextView) view.findViewById(R.id.admin_title);
            tvDescription = (TextView) view.findViewById(R.id.admin_description);

            tvlink = (TextView) view.findViewById(R.id.admin_link);
            tvImage = (ImageView) view.findViewById(R.id.admin_image);
            tvUserID = (TextView) view.findViewById(R.id.admin_userID);

            play = (Button) view.findViewById(R.id.admin_play_button);
            delete = (Button) view.findViewById(R.id.admin_delete_button);

            approve = (Button) view.findViewById(R.id.admin_approve_button);

            //Listeners on every listView items
            tvTitle.setTag(position);
            tvDescription.setTag(position);
            tvImage.setTag(position);
            tvlink.setTag(position);
            approve.setTag(position);
            play.setTag(position);
            delete.setTag(position);
            approved_icon.setTag(position);

            approve.setOnClickListener(approve_listener);
            //  play.setOnClickListener(approve_listener);
            tvDescription.setOnClickListener(approve_listener);
            tvTitle.setOnClickListener(approve_listener);
            tvlink.setOnClickListener(approve_listener);
            tvImage.setOnClickListener(approve_listener);
            delete.setOnClickListener(delete_listener);

            title = adminApprovedModel.getApproved_title();
            description = adminApprovedModel.getApproved_description();
            link = adminApprovedModel.getApproved_link();
            image = adminApprovedModel.getApproved_image();
            userID = adminApprovedModel.getApproved_userID();

            tvTitle.setText(title + "");
            tvDescription.setText(description + "");
            tvlink.setText(link + "");
            tvUserID.setText(userID + "");


            byte[] decodedString = Base64.decode(image + "", Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            tvImage.setImageBitmap(decodedByte);

            refer = FirebaseDatabase.getInstance().getReference("Approved_Links");
            refer2 = FirebaseDatabase.getInstance().getReference("Approved_Counter");
            refer2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    try {
                        map = (Map<String, Object>) dataSnapshot.getValue();
                        x = Integer.parseInt(map.get("number").toString());

                    } catch (Exception e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        catch (Exception g)
        {
            Log.d("masla ", String.valueOf(g));

            Toast.makeText(context, "masla "+g, Toast.LENGTH_SHORT).show();
        }
        return view;
    }



    private View.OnClickListener approve_listener = new View.OnClickListener() {
     @Override
       public void onClick(View v) {
            try {
                Toast.makeText(context, "btn  :" +approve.getText().toString(), Toast.LENGTH_SHORT).show();

                int pos = (Integer) v.getTag();
                int s = v.getId();

                counter=adminApprovedModelList.get(pos).getAdmin_counter();
                approve_status=adminApprovedModelList.get(pos).getAdmin_approvedStatus();
                Toast.makeText(context, "Counter : "+ counter, Toast.LENGTH_SHORT).show();

                approveLinks(pos,counter,approve);


               }catch (Exception e) {
               Toast.makeText(getContext(),"MASLA HAI : " + e.getMessage(), Toast.LENGTH_SHORT).show();
           }}};

    protected void approveLinks(final int position, final String counter, final Button approve){




        refer3= FirebaseDatabase.getInstance().getReference("Links").child("Link "+counter).child("approved_status");
        refer4= FirebaseDatabase.getInstance().getReference("Links").child("Link "+counter).child("approved_status");


        refer3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String myval=dataSnapshot.getValue().toString();

                if(myval.equals("TRUE"))
                {
                    DisApprove(position,approve,counter);
                }
                else
                {
                    Approve(position,approve,counter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


    public void Approve(int position, final Button approve,String counter5) {


        String approved_title=adminApprovedModelList.get(position).getApproved_title();
        String approved_description=adminApprovedModelList.get(position).getApproved_description();
        String approved_link=adminApprovedModelList.get(position).getApproved_link();
        String approved_image=adminApprovedModelList.get(position).getApproved_image();
        String approved_user_id=adminApprovedModelList.get(position).getApproved_userID();
        counter=adminApprovedModelList.get(position).getAdmin_counter();
        approve_status="TRUE";
        approve_status="FALSE";
        fba=FirebaseAuth.getInstance();
        refer= FirebaseDatabase.getInstance().getReference("Approved_Links");
        refer2= FirebaseDatabase.getInstance().getReference("Approved_Counter");

        refer4.setValue(approve_status);

        LinkClass linkclass=new LinkClass(approved_user_id,approved_link,approved_title,approved_description,approved_image,approve_status,counter,feature_status);
        refer.child("Link "+ (y=x+1)).setValue(linkclass);
        String num=String.valueOf(y);
        refer2.child("number").setValue(num);

        Toast.makeText(context,"Successfullyy Approved", Toast.LENGTH_SHORT).show();
        final String finalCounter = counter5;
        refer2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try{
                    map = (Map<String, Object>) dataSnapshot.getValue();
                    x=Integer.parseInt(map.get("number").toString());
                    Toast.makeText(getContext(), "Successed", Toast.LENGTH_SHORT).show();



                    for(int k=0;k<adminApprovedModelList.size();k++) {


                        String mycount=adminApprovedModelList.get(k).getAdmin_counter();
                        Toast.makeText(context, "2", Toast.LENGTH_SHORT).show();


                        Toast.makeText(context, "mycount " + mycount, Toast.LENGTH_SHORT).show();

                        /*

                        String mycount=adminApprovedModelList.get(k).getAdmin_counter();
                        int mycount2=Integer.parseInt(mycount);
                        int finalcounter2=Integer.parseInt(finalCounter);

                        Toast.makeText(context, "mycount "+mycount, Toast.LENGTH_SHORT).show();

                        Toast.makeText(context, "mycount2 "+mycount2, Toast.LENGTH_SHORT).show();

                        Toast.makeText(context, "finalCounter "+finalCounter, Toast.LENGTH_SHORT).show();

                        Toast.makeText(context, "finalcounter2 "+finalcounter2, Toast.LENGTH_SHORT).show();



                        if(mycount2==finalcounter2) {
                            approve.setText("DisApprove");
                        }
                    }

                    */
                    }
                }catch (Exception e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }}
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    public void DisApprove(int position, final Button approve,String counter5) {



        String approved_title=adminApprovedModelList.get(position).getApproved_title();
        String approved_description=adminApprovedModelList.get(position).getApproved_description();
        String approved_link=adminApprovedModelList.get(position).getApproved_link();
        String approved_image=adminApprovedModelList.get(position).getApproved_image();
        String approved_user_id=adminApprovedModelList.get(position).getApproved_userID();
        counter=adminApprovedModelList.get(position).getAdmin_counter();
        approve_status="FALSE";

        fba=FirebaseAuth.getInstance();
        refer= FirebaseDatabase.getInstance().getReference("Approved_Links");
        refer2= FirebaseDatabase.getInstance().getReference("Approved_Counter");


        LinkClass linkclass=new LinkClass(approved_user_id,approved_link,approved_title,approved_description,approved_image,approve_status,counter,"TRUE");
        refer.child("Link "+ (y=x+1)).setValue(linkclass);
        String num=String.valueOf(y);
        refer2.child("number").setValue(num);

        Toast.makeText(context,"Successfullyy Approved", Toast.LENGTH_SHORT).show();
        final String finalCounter = counter5;
        refer2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try{
                    map = (Map<String, Object>) dataSnapshot.getValue();
                    x=Integer.parseInt(map.get("number").toString());
                    Toast.makeText(getContext(), "Successed", Toast.LENGTH_SHORT).show();




                    for(int k=0;k<adminApprovedModelList.size();k++) {
                        String mycount=adminApprovedModelList.get(k).getAdmin_counter();



                        Toast.makeText(context, "1", Toast.LENGTH_SHORT).show();


                        Toast.makeText(context, "mycount "+mycount, Toast.LENGTH_SHORT).show();

                      /*
                        int mycount2=Integer.parseInt(mycount);
                        int finalcounter2=Integer.parseInt(finalCounter);



                        Toast.makeText(context, "mycount "+mycount, Toast.LENGTH_SHORT).show();

                        Toast.makeText(context, "mycount2 "+mycount2, Toast.LENGTH_SHORT).show();

                        Toast.makeText(context, "finalCounter "+finalCounter, Toast.LENGTH_SHORT).show();

                        Toast.makeText(context, "finalcounter2 "+finalcounter2, Toast.LENGTH_SHORT).show();


                        if(mycount2==finalcounter2) {
                            approve.setText("Approve");
                        }

                        */
                        }
                }catch (Exception e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }}
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private View.OnClickListener delete_listener = new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onClick(View v) {
            try{
                int pos = (Integer) v.getTag();
               int i= v.getId();



            }catch (Exception e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }}};

    /*private View.OnClickListener image_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try{
                int pos = (Integer) v.getTag();
                Toast.makeText(getContext(), String.valueOf(pos), Toast.LENGTH_SHORT).show();

            }catch (Exception e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }}};
    private View.OnClickListener title_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try{
                int pos = (Integer) v.getTag();
                Toast.makeText(getContext(), String.valueOf(pos), Toast.LENGTH_SHORT).show();

            }catch (Exception e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }}};
    private View.OnClickListener link_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try{
                int pos = (Integer) v.getTag();
                Toast.makeText(getContext(), String.valueOf(pos), Toast.LENGTH_SHORT).show();

            }catch (Exception e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }}};
    private View.OnClickListener description_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try{
                int pos = (Integer) v.getTag();
                Toast.makeText(getContext(), String.valueOf(pos), Toast.LENGTH_SHORT).show();

            }catch (Exception e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }}};


    private View.OnClickListener play_listener = new View.OnClickListener() {

        @Override

        public void onClick(View v) {

            try {

                    int po = (Integer) v.getTag();
                    Toast.makeText(getContext(), "Play Position "+po, Toast.LENGTH_SHORT).show();

            }catch (Exception e){
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    };
*/




}