package com.myapp.railwayapp.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.myapp.railwayapp.R;


public class ComplainLocationFragment extends BaseFragment implements View.OnClickListener {
    private EditText location;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_complain_location,container,false);
        location=(EditText)view.findViewById(R.id.fragment_complain_location);



        view.findViewById(R.id.fragment_complain_location_back_button).setOnClickListener(this);
        view.findViewById(R.id.fragment_complain_location_next_button).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        int viewId=v.getId();
        if(viewId==R.id.fragment_complain_location_back_button)
        {
            callComplainFragment(new ComplainTypeFragment());
        }
        else if(viewId==R.id.fragment_complain_location_next_button)
        {
            complainLocation=(location.getText().toString());
            callComplainFragment(new ComplainDescriptionFragment());
        }

    }

}
