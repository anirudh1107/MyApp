package com.myapp.railwayapp.Activities;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.myapp.railwayapp.R;


public class FeedbackActivity extends BaseAuthenticatedActivity implements View.OnClickListener {
    private Button feedbackButton;
    private Animation animAlpha;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private EditText feedbackText;
    private final String COMPLAIN_ID = "ComplainId";
    private final String CUSTOMER_MOBILE = "CustomerMobile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        feedbackButton=findViewById(R.id.feedback_submit_button);
        feedbackText=findViewById(R.id.feedback_text);
        animAlpha = AnimationUtils.loadAnimation(this,
                R.anim.anim_alpha);
        feedbackButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.feedback_submit_button)
        {
            String complainId=getIntent().getStringExtra(COMPLAIN_ID);
            String customerMob=getIntent().getStringExtra(CUSTOMER_MOBILE);
            String feedbackDetails=feedbackText.getText().toString();
            view.startAnimation(animAlpha);
            mDatabase=FirebaseDatabase.getInstance();
            mRef=mDatabase.getReference("UserFeedback");
          //ToDO: change firebase reference i.e customer mob 
            mRef.child("482789").child(complainId).setValue(feedbackDetails);
            setResult(RESULT_OK);
            finish();
        }
    }
}
