package com.example.escomputer.augumentedrealityapp.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.escomputer.augumentedrealityapp.Category;
import com.example.escomputer.augumentedrealityapp.ModelClasses.LinkClass;
import com.example.escomputer.augumentedrealityapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

public class Upload_Actitvity extends AppCompatActivity implements View.OnClickListener{
    private EditText title,description,link;
    private ImageView image;
    private Button button_upload_image,upload_button,category_button;
    private DatabaseReference refer;
    private FirebaseAuth fba;
    private static String imageString;
    private int PICK_IMAGE=234;
    private DatabaseReference refer2;
    private static int x,y;
    private Map<String, Object> map;
    private String link_status,feature_status;
    private TextView category_textview;
    String data;
    static  Bitmap myBitmap,bitmap,scaledBitmap;
    private static  String user_title,user_description,user_link;
   static Uri filepath2;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload__actitvity);
        image = (ImageView) findViewById(R.id.image);
        button_upload_image = (Button)findViewById(R.id.button_upload_image);
        upload_button = (Button) findViewById(R.id.upload_button);
        category_button = (Button)findViewById(R.id.category_button);
        category_textview=(TextView) findViewById(R.id.category_textview);
        description  = (EditText) findViewById(R.id.description);
        title  = (EditText) findViewById(R.id.title);
        link  = (EditText) findViewById(R.id.link);

        button_upload_image.setOnClickListener(this);
        upload_button.setOnClickListener(this);
        category_button.setOnClickListener(this);
        imageString="notgiven";
        fba= FirebaseAuth.getInstance();
        refer= FirebaseDatabase.getInstance().getReference("Links");
        refer2= FirebaseDatabase.getInstance().getReference("Counter");
        image.setImageResource(R.drawable.usericon);


        data = getIntent().getStringExtra("data");
        category_textview.setText(data + "");

    }

    @Override
    public void onClick(View view) {
        if(view == upload_button){

            uploadDetails();
        }


        if(view == button_upload_image){

            showFileChooser();
        }

        if(view==category_button){
            user_title=title.getText().toString();
            user_description=description.getText().toString();
            user_link=link.getText().toString();
            Intent i =new Intent (Upload_Actitvity.this,Category.class);
            startActivity(i);

        }
    }
    private void uploadDetails(){




        FirebaseUser firebaseuser = fba.getCurrentUser();
        String id=firebaseuser.getUid();
        link_status="FALSE";
        feature_status="FALSE";
        LinkClass linkclass=new LinkClass(id,user_link,user_title,user_description,imageString,link_status,String.valueOf(x+1),feature_status,data);
        refer.child("Link "+ (y=x+1)).setValue(linkclass);
        String num=String.valueOf(y);
        refer2.child("number").setValue(num);

        FirebaseUser user=fba.getCurrentUser();

        refer2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try{
                    map = (Map<String, Object>) dataSnapshot.getValue();
                    x=Integer.parseInt(map.get("number").toString());
                    Toast.makeText(Upload_Actitvity.this, "Successed", Toast.LENGTH_SHORT).show();



                }catch (Exception e) {
                    Toast.makeText(Upload_Actitvity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }}
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    @Override
    public void onStart() {
        super.onStart();
        if(user_description==null){
            Toast.makeText(this, "des nullling", Toast.LENGTH_SHORT).show();
        }
        else if(user_link==null){
            Toast.makeText(this, " link nullling", Toast.LENGTH_SHORT).show();
        }
        else if(user_title==null){
            Toast.makeText(this, " title nullling", Toast.LENGTH_SHORT).show();
        }
        else if(imageString==null){
            Toast.makeText(this, "image nullling", Toast.LENGTH_SHORT).show();
        }
        else
        {
            description.setText(user_description);
            title.setText(user_title);
            link.setText(user_link);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath2);
            } catch (IOException e) {
                e.printStackTrace();
            }
            scaledBitmap = scaleDown(bitmap, 200, true);
            imageString = imagestorageindatabase(scaledBitmap);
            Toast.makeText(Upload_Actitvity.this, "Sucessfullyyy uploaded", Toast.LENGTH_SHORT).show();
            image.setImageResource(0);
            myBitmap = convertbase64intobitmap(imageString);
            image.setImageBitmap(myBitmap);
            image.setImageBitmap(myBitmap);
        }


        refer2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try{
                    map = (Map<String, Object>) dataSnapshot.getValue();
                    x=Integer.parseInt(map.get("number").toString());

                }catch (Exception e) {
                    Toast.makeText(Upload_Actitvity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }}
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Toast.makeText(Upload_Actitvity.this, "start of ONACTIVITY ", Toast.LENGTH_SHORT).show();

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filepath2 = data.getData();
            imageString = filepath2.toString();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath2);
                 scaledBitmap = scaleDown(bitmap, 200, true);
                imageString = imagestorageindatabase(scaledBitmap);
                Toast.makeText(Upload_Actitvity.this, "Sucessfullyyy uploaded", Toast.LENGTH_SHORT).show();
                image.setImageResource(0);
                myBitmap = convertbase64intobitmap(imageString);
                image.setImageBitmap(myBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }


        } else {
            Toast.makeText(Upload_Actitvity.this, " MASLAAAAA", Toast.LENGTH_SHORT).show();
        }

    }


    Bitmap convertbase64intobitmap(String imgString2) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] imageBytes = baos.toByteArray();
        imageBytes = Base64.decode(imgString2, Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        return decodedImage;
    }


    public String imagestorageindatabase(Bitmap bitmap) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteFormat = stream.toByteArray();
        String encodedImage = Base64.encodeToString(byteFormat, Base64.NO_WRAP);
        return encodedImage;


    }


    private void showFileChooser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(i, "Select an image"), PICK_IMAGE);

        Toast.makeText(Upload_Actitvity.this, "after startactivityRESULT create.chooser", Toast.LENGTH_SHORT).show();
    }


    public static Bitmap scaleDown(Bitmap realImage, float maxImageSize,
                                   boolean filter) {
        float ratio = Math.min(
                (float) maxImageSize / realImage.getWidth(),
                (float) maxImageSize / realImage.getHeight());
        int width = Math.round((float) ratio * realImage.getWidth());
        int height = Math.round((float) ratio * realImage.getHeight());

        Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, width,
                height, filter);
        return newBitmap;
    }

}
