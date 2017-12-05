package com.myapp.railwayapp.Activities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myapp.railwayapp.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by dellpc on 11/10/2017.
 */

public class myAdapter extends ArrayAdapter<word> {

    public myAdapter(@NonNull Context context, int resource, @NonNull List<word> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listView=convertView;
        if(listView==null)
        {
            listView= LayoutInflater.from(getContext()).inflate(R.layout.listitem,parent,false);
        }

        word current=getItem(position);

        TextView id=(TextView)listView.findViewById(R.id.id);
        TextView complaint=(TextView)listView.findViewById(R.id.complaintype);
        TextView topStatus=(TextView)listView.findViewById(R.id.top_status);
        TextView longComplaintId=(TextView)listView.findViewById(R.id.long_customer_id);
        TextView longComplaintType=(TextView)listView.findViewById(R.id.long_complain_type);
        TextView longComplainStatus=(TextView)listView.findViewById(R.id.long_status);
        TextView longNumber =(TextView)listView.findViewById(R.id.long_phone_number);
        LinearLayout onTap=(LinearLayout)listView.findViewById(R.id.on_tap);
        TextView longSubtype=listView.findViewById(R.id.long_complain_Subtype);
        ImageView longimage=listView.findViewById(R.id.image_i);
        LinearLayout imageview=listView.findViewById(R.id.image_view);

        id.setText(current.getCid());
        complaint.setText(current.getType());
        if(current.getStatus()==0)
            topStatus.setText("Pending");
        else if(current.getStatus()==1)
            topStatus.setText("In Progress");
        else
            topStatus.setText("Completed");
        longComplaintId.setText(current.getCid());
        longComplaintType.setText(current.getType());
        longNumber.setText(current.getMobNumber());
        longSubtype.setText(current.getTypeDetail());
        if(current.getStatus()==0)
            longComplainStatus.setText("Pending");
        else if(current.getStatus()==1)
            longComplainStatus.setText("In Progress");
        else
            longComplainStatus.setText("Completed");
        onTap.setVisibility(View.GONE);


        String iuid=current.getImageUID().trim();

        if(iuid.isEmpty())
        {
            imageview.setVisibility(View.GONE);
        }
        else
        {
            Glide.with(longimage.getContext())
                    .load(current.getImageUID())
                    .into(longimage);
        }

        return listView;
    }


}
