package com.example.escomputer.augumentedrealityapp.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.escomputer.augumentedrealityapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SplashScreen extends AppCompatActivity {
    private static int temp=2000;
    private DatabaseReference refer;
    private FirebaseUser currentFirebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        refer= FirebaseDatabase.getInstance().getReference();
        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();

    new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
          try {

if(currentFirebaseUser==null) {
    Toast.makeText(SplashScreen.this, String.valueOf(currentFirebaseUser), Toast.LENGTH_SHORT).show();
    Intent intent = new Intent(SplashScreen.this, MainActivity2.class);
    startActivity(intent);
    finish();

}

 else {
    Toast.makeText(SplashScreen.this, "" + currentFirebaseUser.getUid(), Toast.LENGTH_SHORT).show();
    Intent i = new Intent(SplashScreen.this, MainActivity.class);
    startActivity(i);
    finish();
}
          }catch (Exception e){
              Toast.makeText(SplashScreen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
          }
        }
    },temp);

    }
}
