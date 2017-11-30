package com.myapp.railwayapp.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.myapp.railwayapp.Fragments.ComplainTypeFragmentCallInterface;

import com.myapp.railwayapp.Infrastructure.Auth;
import com.myapp.railwayapp.R;
import com.soundcloud.android.crop.Crop;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ComplainActivity extends BaseAuthenticatedActivity implements ComplainTypeFragmentCallInterface {
    private ProgressDialog pDialog;
    private StorageReference mStorage;
    private ImageView complainImage;
    private View imageProgressFrame;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private File tempOutputFile;
    private Intent data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain);
    }
    public String getUid()
    {
        return mAuth.getCurrentUser().getUid();
    }



    @Override
    public void onComplainTypeClickBack() {
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    public void onComplainTypeClickSubmit() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void getImageCrop(Uri outputFile, Uri tempFileUri,File tempOutputFile,int id) {
        complainImage=findViewById(id);
        this.tempOutputFile=tempOutputFile;
        new Crop(outputFile)
                .asSquare()
                .output(tempFileUri)
                .start(this);
    }

    @Override
    public void onImageSubmit() {
        mStorage= FirebaseStorage.getInstance().getReference();
        pDialog=new ProgressDialog(this);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());
        pDialog.setMessage("Uploading.....");
        pDialog.show();
        Uri uri=Uri.fromFile(tempOutputFile);
        if(uri!=null) {
            StorageReference filepath = mStorage.child("Photos").child(uri.getLastPathSegment()+formattedDate);

            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Successfully added to database", Toast.LENGTH_SHORT).show();
                    mAuth= FirebaseAuth.getInstance();
                    database=FirebaseDatabase.getInstance();
                    reference=database.getReference().child("Complain");
                    DatabaseReference complainNumber=reference.push();
                    complainNumber.child("Type").setValue("");
                    complainNumber.child("Location").setValue("");
                    complainNumber.child("Description").setValue("");
                    complainNumber.child("MobNumber").setValue("");
                    complainNumber.child("Status").setValue(0);
                    complainNumber.child("TypeDetail").setValue("");
                    complainNumber.child("Uid").setValue(mAuth.getCurrentUser().getUid());
                    complainNumber.child("imageUID").setValue(taskSnapshot.getDownloadUrl().toString());
                    setResult(RESULT_OK);
                    finish();
                }
            });
//lol
        }
        else
            Toast.makeText(getApplicationContext(),"No Image Uri Found",Toast.LENGTH_SHORT).show();
    }

    public interface InformationGetter
    {
        String type = null;
        String Location=null;
        String description=null;
        String phoneNumber=null;
        String userName=null;
    }
    public void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        if(resultCode!=RESULT_OK)
        {
            return;
        }
        else if(requestCode==Crop.REQUEST_CROP)
        {
            //todo send tempfileuri to server as new Avatar
            Log.e("f","LOL");
            complainImage.setImageResource(0);
            complainImage.setImageURI(Uri.fromFile(tempOutputFile));


        }
    }
}



