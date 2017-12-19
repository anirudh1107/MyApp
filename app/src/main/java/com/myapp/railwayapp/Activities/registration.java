package com.myapp.railwayapp.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.myapp.railwayapp.R;

public class registration extends BaseActivity {

    private EditText user;
    private EditText password;
    private Button add;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user=(EditText)findViewById(R.id.newuser);
        add=(Button)findViewById(R.id.add1);
        password=(EditText)findViewById(R.id.pass);
        progressDialog=new ProgressDialog(this);


    }

    private void registerUser()
    {
        final String email=user.getText().toString().trim();
        final String pass=password.getText().toString().trim();

        if(TextUtils.isEmpty(email))
        {

            Toast.makeText(this,"please give email address",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(pass))
        {
            Toast.makeText(this,"please enter the password",Toast.LENGTH_SHORT).show();

        }
        progressDialog.setMessage("REGISTERING......");

        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(registration.this,"signup successful",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    openForRegistration();

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(registration.this,"Please try again later",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                }
            }
        });




    }

    void openForRegistration()
    {
        final String email=user.getText().toString().trim();
        final String pass=password.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i=new Intent(registration.this,DetailActivity.class);
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(registration.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                    }
                });
    }
}
