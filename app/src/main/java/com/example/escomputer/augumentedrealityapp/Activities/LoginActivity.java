package com.example.escomputer.augumentedrealityapp.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.escomputer.augumentedrealityapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private Button login_button;
    private EditText login_email,login_password;
    private TextView login_sign_up;
    private ProgressDialog progressdialogue;
    private FirebaseAuth fba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fba=FirebaseAuth.getInstance();

        progressdialogue=new ProgressDialog(this);
        login_button=(Button) findViewById(R.id.login_btn);
        login_email=(EditText) findViewById(R.id.login_email);
        login_password=(EditText) findViewById(R.id.login_password);
        login_sign_up=(TextView) findViewById(R.id.login_signup);

        login_button.setOnClickListener(this);
        login_sign_up.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view==login_button){
            loginUser();

        }
        if(view==login_sign_up){
            Intent i=new Intent(LoginActivity.this,RegistrationActivity.class);
            startActivity(i);
            finish();

        }

    }
    private void loginUser() {
        String email_user = login_email.getText().toString();
        String pass_user = login_password.getText().toString();

        if (TextUtils.isEmpty(email_user)) {
            Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(pass_user)) {
            Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();
        }
        progressdialogue.setMessage("Logging in User...");
        progressdialogue.show();
        fba.signInWithEmailAndPassword(email_user, pass_user).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    checkIfEmailVerified();
                } else {
                    FirebaseAuthException e = (FirebaseAuthException) task.getException();
                    Toast.makeText(LoginActivity.this, "Failed Registration: " + " " + e.getMessage(), Toast.LENGTH_LONG).show();
                    progressdialogue.dismiss();
                    return;

                }
            }
        });
    }
public void checkIfEmailVerified(){
   try{
       FirebaseUser fbu=FirebaseAuth.getInstance().getCurrentUser();

    boolean emailVerified=fbu.isEmailVerified();
    if(!emailVerified){
        Toast.makeText(this, "Verify the email address", Toast.LENGTH_SHORT).show();
        fba.signOut();
        Intent i = new Intent(LoginActivity.this, LoginActivity.class);
        startActivity(i);

    }
        else{
        Toast.makeText(this, "else waali", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}catch (Exception e){
       Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
   }
    }
}