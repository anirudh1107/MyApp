package com.myapp.railwayapp.Others;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.myapp.railwayapp.Activities.word;
import com.myapp.railwayapp.R;

import org.w3c.dom.Text;

import java.util.List;


public class myAdapter extends ArrayAdapter<word> implements View.OnClickListener {
    private SendFeedInterface sendFeed;
    private TextView longComplaintId;
    private TextView id;
    private Button goToFeedbackButton;
    private List<word> complainList;

    public myAdapter(@NonNull Context context, int resource, @NonNull List<word> objects,SendFeedInterface sendFeed) {
        super(context, resource, objects);
        this.sendFeed=sendFeed;
        this.complainList=objects;
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

        id=(TextView)listView.findViewById(R.id.id);
        TextView complaint=(TextView)listView.findViewById(R.id.complaintype);
        TextView topStatus=(TextView)listView.findViewById(R.id.top_status);
        longComplaintId=(TextView)listView.findViewById(R.id.long_customer_id);
        TextView longComplaintType=(TextView)listView.findViewById(R.id.long_complain_type);
        TextView longComplainStatus=(TextView)listView.findViewById(R.id.long_status);
        TextView longNumber =(TextView)listView.findViewById(R.id.long_phone_number);
        TextView longSubtype=listView.findViewById(R.id.long_complain_Subtype);
        ImageView longimage=listView.findViewById(R.id.image_i);
        LinearLayout imageview=listView.findViewById(R.id.image_view);
        LinearLayout listViewWrap=listView.findViewById(R.id.list_item_wrap);
        goToFeedbackButton=listView.findViewById(R.id.send_feedback_button);

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


        String iuid=current.getImageUID().trim();

        if(iuid.isEmpty())
        {
            imageview.setVisibility(View.GONE);
        }
        else
        {
            imageview.setVisibility(View.VISIBLE);
            Glide.with(longimage.getContext())
                    .load(current.getImageUID())
                    .into(longimage);
        }
        goToFeedbackButton.setTag(position);
        goToFeedbackButton.setOnClickListener(this);
        listViewWrap.setOnClickListener(this);
        return listView;
    }


    @Override
    public void onClick(View view) {


        if(view.getId()==R.id.list_item_wrap)
        {

            LinearLayout onTap=(view.findViewById(R.id.on_tap));
            if(onTap.getVisibility()==View.GONE)
            {
                onTap.setVisibility(View.VISIBLE);
            }
            else
            {
                onTap.setVisibility(View.GONE);
            }
        }
        if(view.getId()==R.id.send_feedback_button)
        {
            if(view.getTag()!=null) {
                word feedbackWord = complainList.get((Integer) view.getTag());
                Log.e("T1", "Test");
                sendFeed.goTofeedback(feedbackWord.getCid(), feedbackWord.getMobNumber());
            }
            else
                Log.e("T2", "Test2");
        }
    }

}
