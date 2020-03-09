package com.example.escomputer.augumentedrealityapp.Activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.escomputer.augumentedrealityapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.URI;
import java.util.Map;

public class Testing extends AppCompatActivity implements View.OnClickListener{
    private TextView text;
    private  EditText t2,t1,t3;
    private Button send;
    private FirebaseAuth fba;
    private String TO,SUBJECT,MESSAGE;
Intent intent;
    private int count;
    private String url;
    private static int temp;
    DatabaseReference counter = FirebaseDatabase.getInstance().getReference("Counter");
    DatabaseReference counter2 = counter.child("number");

    DatabaseReference link = FirebaseDatabase.getInstance().getReference("Links");
private ImageButton imgbtn;
private VideoView videoview;
private ProgressDialog pd;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);


        url= getIntent().getStringExtra("link");


        WebView browser = (WebView) findViewById(R.id.videoview);
        WebSettings webSettings = browser.getSettings();
        webSettings.setJavaScriptEnabled(true);
        browser.loadUrl(""+url);

        //Toast.makeText(this, ""+ CompanyURLFragment.selected_url, Toast.LENGTH_SHORT).show();

    }



 @Override
        public void onClick(View view) {

        }

    }
