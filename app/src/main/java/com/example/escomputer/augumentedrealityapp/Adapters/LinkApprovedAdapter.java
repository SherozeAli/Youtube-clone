package com.example.escomputer.augumentedrealityapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.escomputer.augumentedrealityapp.Activities.Testing;
import com.example.escomputer.augumentedrealityapp.ModelClasses.LinkClass;
import com.example.escomputer.augumentedrealityapp.ModelClasses.MyLinkModel;
import com.example.escomputer.augumentedrealityapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LinkApprovedAdapter extends ArrayAdapter {

    Context context;
    List<MyLinkModel> myLinkModelList = new ArrayList<MyLinkModel>();
    ImageView tvimageview;
    TextView tvTitle,tvUserId,tvDescription,tvlink,tvCategory;
    Button play,approve,feature;
    private DatabaseReference refer,refer2,refer3,refer4;
    private FirebaseAuth fba;
    private String counter,approved_status,featured_status;
    private int y,x;
    private Map<String, Object> map;


    public LinkApprovedAdapter(@NonNull Context context, List<MyLinkModel> myLinkModelList) {
        super(context, R.layout.mylinkapproved, myLinkModelList);
        this.context = context;
        this.myLinkModelList=myLinkModelList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.mylinkapproved, parent, false);

        MyLinkModel myLinkModel = myLinkModelList.get(position);
        //  Log.d("adapterArrayList : ",hotelarrlist.size()+"");

        tvimageview = (ImageView) view.findViewById(R.id.admin_image);
        tvTitle = (TextView) view.findViewById(R.id.admin_title);
        tvUserId = (TextView) view.findViewById(R.id.admin_userID);
        tvDescription = (TextView) view.findViewById(R.id.admin_description);
        tvlink = (TextView) view.findViewById(R.id.admin_link);
        tvCategory = (TextView) view.findViewById(R.id.admin_category);
        play = (Button) view.findViewById(R.id.admin_play_button);
        feature = (Button) view.findViewById(R.id.admin_feature_button);
        approve = (Button) view.findViewById(R.id.admin_approve_button);


        String title = myLinkModel.getTitle() + "";
        String descr = myLinkModel.getDescription() + "";
        String link = myLinkModel.getLink() + "";
        String image = myLinkModel.getImagestring();
        String use_id = myLinkModel.getUsrid();
        String cat=myLinkModel.getCategory();
//        String status = myLinkModel.getStatus();


        tvTitle.setText(title + "");
        tvDescription.setText(descr + "");
        tvlink.setText(link + "");
        tvUserId.setText(use_id + "");
        tvCategory.setText(cat+"");

        byte[] decodedString = Base64.decode(image + "", Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        tvimageview.setImageBitmap(decodedByte);

        tvTitle.setTag(position);
        tvDescription.setTag(position);
        tvimageview.setTag(position);
        tvlink.setTag(position);
        approve.setTag(position);
        play.setTag(position);
        feature.setTag(position);


        approve.setOnClickListener(approve_listener);
         play.setOnClickListener(play_listener);
        tvDescription.setOnClickListener(approve_listener);
        tvTitle.setOnClickListener(approve_listener);
        tvlink.setOnClickListener(approve_listener);
        tvimageview.setOnClickListener(approve_listener);
        feature.setOnClickListener(feature_listener);



        String f_status=myLinkModelList.get(position).getFeature_status()+"";

        if(f_status.equals("TRUE"))
        {
            feature.setText("Remove from features");
        }
        else
        {
            feature.setText("Add to features");
        }

            String status4=myLinkModelList.get(position).getStatus()+"";
            if(status4.equals("TRUE"))
            {
                approve.setText("DisApprove");
            }
            else
            {
                approve.setText("Approve");
            }




        return view;
}

    private View.OnClickListener play_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try{
                int pos = (Integer) v.getTag();
                int s = v.getId();
                String links=myLinkModelList.get(pos).getLink();
                Toast.makeText(context, "Link is : "+ links, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), Testing.class);
                intent.putExtra("link", links);
                context.startActivity(intent);

            }catch (Exception e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }};



    private View.OnClickListener feature_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try{
                int pos = (Integer) v.getTag();
                int s = v.getId();
                counter=myLinkModelList.get(pos).getLinkcounter();
                featured_status=myLinkModelList.get(pos).getFeature_status();

                Toast.makeText(context, "Counter : "+ counter, Toast.LENGTH_SHORT).show();

               featuredVideos(pos,counter,featured_status,feature);

            }catch (Exception e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }}};

private void featuredVideos(int position,String counter,String status,Button btn){
    Toast.makeText(context, ""+counter+"  "+status  , Toast.LENGTH_SHORT).show();
    refer3= FirebaseDatabase.getInstance().getReference("Links").child("Link "+counter).child("feature_status");


    if(status.equals("TRUE"))
    {
        featured_status = "FALSE";
    }
    else {
        featured_status = "TRUE";
    }

      refer3.setValue(featured_status);


}
    private View.OnClickListener approve_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                Toast.makeText(context, "btn  :" +approve.getText().toString(), Toast.LENGTH_SHORT).show();

                int pos = (Integer) v.getTag();
                int s = v.getId();
                counter=myLinkModelList.get(pos).getLinkcounter();
                approved_status=myLinkModelList.get(pos).getStatus();

                Toast.makeText(context, "Counter : "+ counter, Toast.LENGTH_SHORT).show();

                approveLinks(pos,counter,approved_status,approve);


            }catch (Exception e) {
                Toast.makeText(getContext(),"MASLA HAI : " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }}};

    protected void approveLinks(int position,String counter,String status,Button btn) {


        refer4= FirebaseDatabase.getInstance().getReference("Links").child("Link "+counter).child("approved_status");


        if(status.equals("TRUE"))
        {
            approved_status = "FALSE";
        }
        else {
            approved_status = "TRUE";
        }
        fba=FirebaseAuth.getInstance();


        Toast.makeText(context,"Successfullyy Approved", Toast.LENGTH_SHORT).show();
         refer4.setValue(approved_status);


    }

}
