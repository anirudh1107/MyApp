package com.myapp.railwayapp.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.myapp.railwayapp.R;

/**
 * Created by Stan on 11/18/2017.
 */

public class ComplainSelectModeFragment extends BaseFragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_complain_selectmode,container,false);
        view.findViewById(R.id.fragment_complain_selectmode_imagebutton).setOnClickListener(this);
        view.findViewById(R.id.fragment_complain_selectmode_detailsbutton).setOnClickListener(this);
        view.findViewById(R.id.fragment_complain_selectmode_backbutton).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        int viewId=v.getId();
        if(viewId==R.id.fragment_complain_selectmode_imagebutton)
        {
            callComplainFragment(new ComplainViaImageFragment());
        }
         if(viewId==R.id.fragment_complain_selectmode_detailsbutton)
        {
            callComplainFragment(new ComplainTypeFragment());
        }
        if(viewId==R.id.fragment_complain_selectmode_backbutton)
        {
            if(callBacks!=null)
            callBacks.onComplainTypeClickBack();
        }
    }

}
