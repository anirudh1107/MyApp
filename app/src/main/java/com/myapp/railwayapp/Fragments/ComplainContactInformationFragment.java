package com.myapp.railwayapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.myapp.railwayapp.Activities.ComplainActivity;
import com.myapp.railwayapp.R;


public class ComplainContactInformationFragment extends BaseFragment implements View.OnClickListener {
    private EditText phonenumber;
    com.myapp.railwayapp.Fragments.ComplainInformation complainInformation;
    ComplainActivity activity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_complain_contactinformation,container,false);
        phonenumber=(EditText)view.findViewById(R.id.fragment_complain_mobilenumber);
        complainInformation=new com.myapp.railwayapp.Fragments.ComplainInformation();
        view.findViewById(R.id.fragment_complain_contactinformation_back_button).setOnClickListener(this);
        view.findViewById(R.id.fragment_complain_contactinformation_submit_button).setOnClickListener(this);
        activity=(ComplainActivity) getActivity();
        Log.e("sd","contact info");
        return view;
    }

    @Override
    public void onClick(View v) {
        int viewId=v.getId();
        if(viewId== R.id.fragment_complain_contactinformation_back_button)
        {
            callComplainFragment(new ComplainDescriptionFragment());
        }
         if(viewId== R.id.fragment_complain_contactinformation_submit_button)
        {
            Log.e("fsa","submit");

                Log.e("fsa","sss");
                mobileNumber = phonenumber.getText().toString();
                complainInformation.setComplainType(complainType);
                complainInformation.setLocation(complainLocation);
                complainInformation.setDescription(complainDescription);
                complainInformation.setMobileNumber(mobileNumber);
                complainInformation.setStatus(status);
                complainInformation.setComplainTypeDetail(complainTypeDetail);
                Toast.makeText(getActivity()," phone number", Toast.LENGTH_SHORT);
                onSubmit();

        }
    }
    private void onSubmit()
    {
        //stote info to database
        if(callBacks!=null){
          toDataBase();
        callBacks.onComplainTypeClickSubmit();
        }
    }

    private void toDataBase() {
        new SetComplainToDatabase(complainInformation.getComplainType(),complainInformation.getLocation(),
                                    complainInformation.getDescription(),
                complainInformation.getMobileNumber(),complainInformation.getStatus(),
                complainInformation.getComplainTypeDetail(),((ComplainActivity)getActivity()).getUid()).addToDataBase();

    }
     class SetComplainToDatabase
    {
        private final String complainType;
        private final String complainLocation;
        private final String complainDescription;
        private final String mobileNumber;
        private final int status;
        private final String complainTypeDetail;
        private final String uid;
        FirebaseDatabase mDataBase;
        DatabaseReference mRefComplain;

        public SetComplainToDatabase(String complainType, String complainLocation,
                                     String complainDescription, String mobileNumber, int status,
                                     String complainTypeDetail, String uid)
        {

           this.complainType=complainType;
            this.complainLocation=complainLocation;
            this.complainDescription=complainDescription;
            this.mobileNumber=mobileNumber;
            mDataBase=FirebaseDatabase.getInstance();
             mRefComplain=mDataBase.getReference("Complain");
            this.status=status;
            this.uid=uid;
            this.complainTypeDetail=complainTypeDetail;
        }
        public void addToDataBase()
        {

            DatabaseReference complainNumber=mRefComplain.push();
            complainNumber.child("Type").setValue(complainType);
            complainNumber.child("Location").setValue(complainLocation);
            complainNumber.child("Description").setValue(complainDescription);
            complainNumber.child("MobNumber").setValue(mobileNumber);
            complainNumber.child("Status").setValue(status);
            complainNumber.child("TypeDetail").setValue(complainTypeDetail);
            complainNumber.child("Uid").setValue(uid);
            complainNumber.child("imageUID").setValue("");
        }

    }

}
