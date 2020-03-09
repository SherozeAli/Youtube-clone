package com.example.escomputer.augumentedrealityapp.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import com.example.escomputer.augumentedrealityapp.ModelClasses.AdminApprovedModel;
import com.example.escomputer.augumentedrealityapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdminActivityAdaptor extends ArrayAdapter {
    Context context;
    List<AdminApprovedModel> model = new ArrayList<AdminApprovedModel>();
    private Button approve,play,delete;
    private  TextView tvTitle,tvDescription,tvlink,tvUserID;
    private ImageView tvImage;
    private DatabaseReference refer,refer2,refer3,refer4;
    private FirebaseAuth fba;
    private String title,description,image,link,userID,approve_status,counter;
    AdminApprovedModel adminApprovedModel;
    int p;
    private static int x,y;
    private Map<String, Object> map;
    private ImageView approved_icon;
    public AdminActivityAdaptor(@NonNull Context context, List<AdminApprovedModel> model) {
        super(context, R.layout.admin_approval_layout, model);
        this.context = context;
        this.model=model;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.admin_approval_layout, parent, false);

         adminApprovedModel= model.get(position);
       // Log.d("adapterArrayList : ",hotelarrlist.size()+"");

        tvTitle = (TextView) view.findViewById(R.id.admin_title);
        tvDescription = (TextView) view.findViewById(R.id.admin_description);

        tvlink = (TextView) view.findViewById(R.id.admin_link);
        tvImage = (ImageView) view.findViewById(R.id.admin_image);
        tvUserID = (TextView) view.findViewById(R.id.admin_userID);

        play = (Button) view.findViewById(R.id.admin_play_button);
        delete = (Button) view.findViewById(R.id.admin_delete_button);
        approve = (Button) view.findViewById(R.id.admin_approve_button);

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



        return view;
    }


}
