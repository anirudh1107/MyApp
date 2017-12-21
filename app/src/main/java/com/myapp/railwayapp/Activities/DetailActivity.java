package com.myapp.railwayapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.myapp.railwayapp.R;

public class DetailActivity extends BaseAuthenticatedActivity {
    EditText newName;
    EditText newAdd;
    EditText newMob;
    Button newSub;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        newName=findViewById(R.id.reg_name);
        newAdd=findViewById(R.id.reg_add);
        newMob=findViewById(R.id.reg_mobile);
        newSub=findViewById(R.id.reg_submit);
        mAuth=FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();
        myRef=database.getReference("UserNew");
        //myanmarfna
        newSub.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                if(newName.getText().toString().isEmpty())
                {
                    Toast.makeText(DetailActivity.this,"Username cannot be empty",Toast.LENGTH_SHORT).show();
                }
                else if(newAdd.getText().toString().isEmpty())
                {
                    Toast.makeText(DetailActivity.this,"Address field cannot be empty",Toast.LENGTH_SHORT).show();
                }
                else if(newMob.getText().toString().trim().length()<10)
                {
                    Toast.makeText(DetailActivity.this,"Mobile Number must be 10 digit",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    DatabaseReference currentUser= myRef.child(mAuth.getCurrentUser().getUid());
                    currentUser.child("Username").setValue(newName.getText().toString());
                    currentUser.child("Address").setValue(newAdd.getText().toString());
                    currentUser.child("Mobile").setValue(newMob.getText().toString());
                    Intent i=new Intent(DetailActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();

                }

            }
        });
    }
}
