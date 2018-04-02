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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.activity_main_complainbutton).setOnClickListener(this);
        findViewById(R.id.activity_main_statusbutton).setOnClickListener(this);
        findViewById(R.id.logoutbutton).setOnClickListener(this);




    }
    @Override
    public void onClick(View view) {
        int viewId=view.getId();

        if(viewId==R.id.activity_main_complainbutton)
        {

                startActivityForResult(new Intent(this,ComplainActivity.class),COMPLAIN_REQUEST_CODE);
        }
        if (viewId==R.id.activity_main_statusbutton)
        {
                Intent i=new Intent(this,complaint_listner.class);
                startActivity(i);
        }
        if(viewId==R.id.logoutbutton)
        {
            application.getAuth().getUser().setLoggedIn(false);
            mAuth.signOut();
            Toast.makeText(this,"LogOutSuccessfull",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,LoginActivity.class));

            finish();
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
        super.onActivityResult(requestCode, resultCode, data);
    }
}
