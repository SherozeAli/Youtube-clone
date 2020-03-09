package com.example.escomputer.augumentedrealityapp.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.escomputer.augumentedrealityapp.ModelClasses.FavouriteModel;
import com.example.escomputer.augumentedrealityapp.ModelClasses.MyLinkModel;
import com.example.escomputer.augumentedrealityapp.R;
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

public class MyfavouriteAdaptor  extends ArrayAdapter  {

    Context context;
    List<FavouriteModel> myLinkModelList = new ArrayList<FavouriteModel>();
    ImageView tvimageview;
    TextView tvTitle,tvUserId,tvDescription,tvlink,tvCategory;
    Button play,approve,feature;
    private DatabaseReference refer,refer2,refer3,refer4;
    private FirebaseAuth fba;
    private FirebaseUser user;
    private String counter,approved_status,featured_status;
    private int y,x;
    private Map<String, Object> map;
    private ImageView tvFavourite;
    String count;


    public MyfavouriteAdaptor(@NonNull Context context, List<FavouriteModel> myLinkModelList) {
        super(context, R.layout.myfavouritelayout, myLinkModelList);
        this.context = context;
        this.myLinkModelList=myLinkModelList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.myfavouritelayout, parent, false);

        FavouriteModel myfavModel = myLinkModelList.get(position);
        //  Log.d("adapterArrayList : ",hotelarrlist.size()+"");
        fba=FirebaseAuth.getInstance();
        user=fba.getCurrentUser();

        tvimageview = (ImageView) view.findViewById(R.id.image);
        tvTitle = (TextView) view.findViewById(R.id.title);
        tvUserId = (TextView) view.findViewById(R.id.userID);
        tvDescription = (TextView) view.findViewById(R.id.description);
        tvlink = (TextView) view.findViewById(R.id.link);
        tvCategory = (TextView) view.findViewById(R.id.category);
       tvFavourite=(ImageView) view.findViewById(R.id.favourite);


        String title = myfavModel.getTitile() + "";
        String descr = myfavModel.getDes() + "";
        String link = myfavModel.getLink() + "";
        String image = myfavModel.getImage();
        String use_id = myfavModel.getId();
        String cat = myfavModel.getCat();


        tvTitle.setText(title + "");
        tvDescription.setText(descr + "");
        tvlink.setText(link + "");
        tvUserId.setText(use_id + "");
        tvCategory.setText(cat + "");

        count=myLinkModelList.get(position).getLinkcounter();

        byte[] decodedString = Base64.decode(image + "", Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        tvimageview.setImageBitmap(decodedByte);

        Toast.makeText(context, "counterr   "+count, Toast.LENGTH_SHORT).show();
           refer2= FirebaseDatabase.getInstance().getReference("Favourites").child(user.getUid()).child("Link "+Integer.parseInt(count));






        return view;


    }}