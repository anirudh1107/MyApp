package com.myapp.railwayapp.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.myapp.railwayapp.Activities.ComplainActivity;
import com.myapp.railwayapp.Infrastructure.Auth;
import com.myapp.railwayapp.R;


public class BaseFragment extends Fragment implements ComplainActivity.InformationGetter{
    public ComplainTypeFragmentCallInterface callBacks;
    public static String complainType;
    public static String complainLocation="fdg";
    public static String complainDescription;
    public static String mobileNumber;
    public static String complainTypeDetail="";
    public static int status;
    Auth auth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        status=0;
       // auth=ComplainActivity.auth;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callBacks=(ComplainTypeFragmentCallInterface)activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callBacks=null;
    }
    protected void callComplainFragment(Fragment fragment)
    {
        FragmentManager manager=getFragmentManager();
        if(manager!=null)
        {FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.activity_complain_fragmentcontainer,fragment);
        transaction.commit();}
    }
}

