package com.myapp.railwayapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.myapp.railwayapp.Others.SendFeedInterface;
import com.myapp.railwayapp.Others.myAdapter;
import com.myapp.railwayapp.R;

import java.util.ArrayList;
import java.util.List;

public class complaint_listner extends BaseAuthenticatedActivity {
    private static final int FEEDBACK_REQUEST = 132;
    private static final String COMPLAIN_ID = "ComplainId";
    private static final String CUSTOMER_MOBILE = "CustomerMobile";
    FirebaseAuth mAuth;
    FirebaseDatabase mDatabase;
    DatabaseReference myRef;
    DatabaseReference check;
    private List<word> list1;
    ListView list_View;
    private ChildEventListener mChildEventListner;
    myAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_listner);
//l0l

        mAuth=FirebaseAuth.getInstance();
        mDatabase=FirebaseDatabase.getInstance();
        myRef=mDatabase.getReference().child("Complain");

        list1=new ArrayList<word>();
        list_View=findViewById(R.id.list_view1);
        adapter=new myAdapter(this, R.layout.listitem, list1, new SendFeedInterface() {
            @Override
            public void goTofeedback(String complainID,String customerMobile) {
                Intent intent=new Intent(getApplicationContext(),FeedbackActivity.class);
                intent.putExtra(COMPLAIN_ID,complainID);
                intent.putExtra(CUSTOMER_MOBILE,customerMobile);
                startActivityForResult(intent,FEEDBACK_REQUEST);
            }
        });

        list_View.setAdapter(adapter);

        mChildEventListner=new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                word newWord = dataSnapshot.getValue(word.class);
                String key=dataSnapshot.getKey();
                newWord.setKey(key);
                if((mAuth.getCurrentUser().getUid()).equals(newWord.getUid()))
                    adapter.add(newWord);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        myRef.addChildEventListener(mChildEventListner);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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
    }
}
