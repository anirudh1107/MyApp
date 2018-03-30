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
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.myapp.railwayapp.R;

public class registration extends AppCompatActivity implements View.OnClickListener {

    private EditText user;
    private EditText password;
    private EditText newName;
    private EditText newAdd;
    private EditText newMob;
    private Button newSub;
    private Spinner dropDown;
    FirebaseDatabase database;
    DatabaseReference myRef;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        init();
        newSub.setOnClickListener(this);

    }


    private void init()
    {
        user=findViewById(R.id.newuser);
        password=findViewById(R.id.pass);
        newName=findViewById(R.id.reg_name);
        newAdd=findViewById(R.id.reg_add);
        newMob=findViewById(R.id.reg_mobile);
        newSub=findViewById(R.id.reg_submit);
        dropDown=findViewById(R.id.locality);
        mAuth=FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();
    }

    @Override
    public void onClick(View view) {

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

        if(newName.getText().toString().isEmpty())
        {
            Toast.makeText(registration.this,"Username cannot be empty",Toast.LENGTH_SHORT).show();
        }
        if(newAdd.getText().toString().isEmpty())
        {
            Toast.makeText(registration.this,"Address field cannot be empty",Toast.LENGTH_SHORT).show();
        }
        if(newMob.getText().toString().trim().length()<10)
        {
            Toast.makeText(registration.this,"Mobile Number must be 10 digit",Toast.LENGTH_SHORT).show();
        }
        if(String.valueOf(dropDown.getSelectedItem()).equalsIgnoreCase("NONE"))
        {
            Toast.makeText(registration.this,"Please Select a locality",Toast.LENGTH_SHORT).show();
        }
        else
        {

            myRef=database.getReference("UserNew").push();
            myRef.child("EmailId").setValue(user.getText().toString());
            myRef.child("Password").setValue(user.getText().toString());
            myRef.child("Username").setValue(newName.getText().toString());
            myRef.child("Address").setValue(newAdd.getText().toString());
            myRef.child("Mobile").setValue(newMob.getText().toString());
            myRef.child("Locality").setValue(String.valueOf(dropDown.getSelectedItem()));

            Toast.makeText(registration.this,"Request Send",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(registration.this,LoginActivity.class));
            finish();

        }
    }


}
