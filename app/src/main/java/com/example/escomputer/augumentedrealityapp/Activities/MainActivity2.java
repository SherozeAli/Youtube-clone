package com.example.escomputer.augumentedrealityapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.escomputer.augumentedrealityapp.R;

public class MainActivity2 extends AppCompatActivity {
private Button btn;
private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        text=(TextView) findViewById(R.id.text);
        btn=(Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity2.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
