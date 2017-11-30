package com.myapp.railwayapp.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.myapp.railwayapp.Activities.ComplainActivity;
import com.myapp.railwayapp.R;


public class ComplainDescriptionFragment extends BaseFragment implements View.OnClickListener {
    private EditText description;
    com.myapp.railwayapp.Fragments.ComplainInformation complainInformation;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_complain_description,container,false);
        description=(EditText)view.findViewById(R.id.fragment_complain_description);
        complainInformation=new com.myapp.railwayapp.Fragments.ComplainInformation();
        view.findViewById(R.id.fragment_complain_description_back_button).setOnClickListener(this);
        view.findViewById(R.id.fragment_complain_description_submit_button).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        int viewId=v.getId();
        if(viewId== R.id.fragment_complain_description_back_button)
        {
            callComplainFragment(new ComplainSelectModeFragment());
        }
         if(viewId== R.id.fragment_complain_description_submit_button)
        {
            complainDescription=(description.getText().toString());

            complainInformation.setComplainType(complainType);
            complainInformation.setDescription(complainDescription);
            complainInformation.setStatus(status);
            complainInformation.setComplainTypeDetail(complainTypeDetail);

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
        new SetComplainToDatabase(complainInformation.getComplainType(),
                                complainInformation.getDescription(),
                                complainInformation.getStatus(),
                                complainInformation.getComplainTypeDetail(),
                                ((ComplainActivity)getActivity()).getUid())
                                .addToDataBase();

    }

    class SetComplainToDatabase
    {
        private final String complainType;
        private final String complainDescription;
        private final int status;
        private final String complainTypeDetail;
        private final String uid;
        FirebaseDatabase mDataBase;
        DatabaseReference mRefComplain;

        public SetComplainToDatabase(String complainType,
                                     String complainDescription, int status,
                                     String complainTypeDetail, String uid)
        {

            this.complainType=complainType;
            this.complainDescription=complainDescription;
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
            complainNumber.child("Description").setValue(complainDescription);
            complainNumber.child("Status").setValue(status);
            complainNumber.child("TypeDetail").setValue(complainTypeDetail);
            complainNumber.child("Uid").setValue(uid);
            complainNumber.child("imageUID").setValue("");
        }

    }

}
