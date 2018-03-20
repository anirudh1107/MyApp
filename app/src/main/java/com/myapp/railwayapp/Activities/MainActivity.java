package com.myapp.railwayapp.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.myapp.railwayapp.R;

public class MainActivity extends BaseAuthenticatedActivity implements View.OnClickListener {
    private static final int COMPLAIN_REQUEST_CODE = 1;
    private SharedPreferences sharedPreferences;
    private DatabaseReference check;
    private static final int FEEDBACK_REQUEST = 132;
    RelativeLayout complain_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences=getSharedPreferences("loginInfo",MODE_PRIVATE);
        final SharedPreferences.Editor editor=sharedPreferences.edit();
        check=FirebaseDatabase.getInstance().getReference().child("User");
        check.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.hasChild(mAuth.getCurrentUser().getUid()))
                {
                    editor.putBoolean("status",true);
                    editor.apply();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"not yet registered by user",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        findViewById(R.id.activity_main_complainbutton).setOnClickListener(this);
        findViewById(R.id.activity_main_statusbutton).setOnClickListener(this);
        findViewById(R.id.logoutbutton).setOnClickListener(this);
        findViewById(R.id.activity_main_profilebutton).setOnClickListener(this);
        findViewById(R.id.activity_main_feedback_button).setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        int viewId=view.getId();

        if(viewId==R.id.activity_main_complainbutton)
        {
            if(sharedPreferences.getBoolean("status",false))
            {
                startActivityForResult(new Intent(this,ComplainActivity.class),COMPLAIN_REQUEST_CODE);
            }
            else
                Toast.makeText(this,"You Are not registered user",Toast.LENGTH_SHORT).show();
        }
        if (viewId==R.id.activity_main_statusbutton)
        {
            if(sharedPreferences.getBoolean("status",false))
            {
                Intent i=new Intent(this,complaint_listner.class);
                startActivity(i);
            }
            else
                Toast.makeText(this,"You Are not registered user",Toast.LENGTH_SHORT).show();
        }
        if(viewId==R.id.logoutbutton)
        {
            application.getAuth().getUser().setLoggedIn(false);

            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.clear();
            editor.commit();
            mAuth.signOut();
            Toast.makeText(this,"LogOutSuccessfull",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,LoginActivity.class));

            finish();
        }
        if(viewId==R.id.activity_main_profilebutton)
        {
            Intent i =new Intent(this,DetailActivity.class);
            startActivity(i);
        }
        if(viewId==R.id.activity_main_feedback_button)
        {
            Intent intent=new Intent(this,FeedbackActivity.class);
            startActivityForResult(intent,FEEDBACK_REQUEST);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1)
        {
            if(resultCode!=RESULT_OK)
            {
                Toast.makeText(this,"No Complain Registered",Toast.LENGTH_SHORT).show();
                return;
            }
            else if(resultCode==RESULT_OK)
            {
                if(requestCode==COMPLAIN_REQUEST_CODE)
                {

                    Toast.makeText(this,"Complain Submitted",Toast.LENGTH_SHORT).show();
                }
            }

        }
        if(requestCode==FEEDBACK_REQUEST)
        {
            if(resultCode==RESULT_OK)
            {
                Toast.makeText(this,"Feedback Successfully Submitted",Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this,"Feedback failed to submit",Toast.LENGTH_SHORT).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
