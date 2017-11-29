package com.myapp.railwayapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.myapp.railwayapp.R;

import java.util.ArrayList;
import java.util.List;

public class ComplainTypeFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private static List<String> list=new ArrayList<>();
    private Spinner dependentSpinner;
    private View dependentSpinnerContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup viewGroup, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_complaintype,viewGroup,false);

        spinner=view.findViewById(R.id.activity_complain_spinner);
        dependentSpinner=view.findViewById(R.id.activity_complain_spinnerdependent);
        dependentSpinnerContainer=view.findViewById(R.id.activity_complain_spinnerdependentcontainer);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(getActivity(),R.array.complain_categories,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

       spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        view.findViewById(R.id.activity_complain_nextbutton).setOnClickListener(this);
        view.findViewById(R.id.activity_complain_back_button).setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        int viewId=v.getId();
        if(viewId==R.id.activity_complain_back_button)
        {
           callComplainFragment(new ComplainSelectModeFragment());
        }
        else if(viewId==R.id.activity_complain_nextbutton) {

              if(!complainType.equals("None"))
                callComplainFragment(new ComplainLocationFragment());
            else
                Toast.makeText(getActivity(),"No Item Selected",Toast.LENGTH_SHORT).show();

        }
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            complainType = (String) parent.getSelectedItem();
            int res = -1;
            if (complainType.equals("Masson"))
            {res = R.array.masson;

            }
            if(complainType.equals("Plumber"))
                res=R.array.plumber;
            if(complainType.equals("Periodic"))
                res=R.array.periodic;
            if (res != -1) {
                dependentSpinner.setVisibility(View.VISIBLE);
                dependentSpinnerContainer.setVisibility(View.VISIBLE);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), res,
                        android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(R.layout.my_spinner);
                dependentSpinner.setAdapter(adapter);
                dependentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        complainTypeDetail= (String) parent.getSelectedItem();
                        Toast.makeText(getActivity(),"Detail"+complainTypeDetail,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
            else
            {
                Toast.makeText(getActivity(),complainType,Toast.LENGTH_SHORT).show();
                dependentSpinner.setVisibility(View.GONE);
                dependentSpinnerContainer.setVisibility(View.GONE);
            }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
