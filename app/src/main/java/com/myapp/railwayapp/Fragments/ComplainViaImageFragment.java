package com.myapp.railwayapp.Fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.myapp.railwayapp.Activities.ComplainActivity;
import com.myapp.railwayapp.R;
import com.soundcloud.android.crop.Crop;
import com.soundcloud.android.crop.CropImageActivity;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Stan on 11/18/2017.
 */

public class ComplainViaImageFragment extends BaseFragment implements View.OnClickListener {
    private static final int REQUEST_SELECT_IMAGE = 100;
    private static final int PIC_CROP = 6709;
    private File tempOutputFile;
    private ProgressDialog pDialog;
    private StorageReference mStorage;
    private ImageView complainImage;
    private View imageProgressFrame;
    private View view;
    private static final int GALLERY_INTENT=1;

    private Uri croppedUri;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedState)
    {
        View view=inflater.inflate(R.layout.fragment_complain_via_image,container,false);
        view.findViewById(R.id.fragment_complain_via_image_backbutton).setOnClickListener(this);
        view.findViewById(R.id.fragment_complain_via_image_submit_button).setOnClickListener(this);
        view.findViewById(R.id.fragment_complain_via_image_addimagebutton).setOnClickListener(this);
        view.findViewById(R.id.fragment_via_image_imagecontainer).setOnClickListener(this);
        this.view=view;
        pDialog=new ProgressDialog(getActivity());
        mStorage= FirebaseStorage.getInstance().getReference();
        complainImage=(ImageView)view.findViewById(R.id.fragment_via_image_image);
        tempOutputFile=new File(getActivity().getExternalCacheDir(),"temp-img.jpg");
        return view;
    }

    @Override
    public void onClick(View v) {
        int viewId=v.getId();
        if(viewId==R.id.fragment_complain_via_image_backbutton)
        {
            callComplainFragment(new ComplainSelectModeFragment());
        }
        if (viewId==R.id.fragment_complain_via_image_submit_button)
        {

            onSubmit();

        }
        if(viewId==R.id.fragment_via_image_imagecontainer||viewId==R.id.fragment_complain_via_image_addimagebutton)
        {
            if(tempOutputFile!=null)
                tempOutputFile.delete();
            tempOutputFile=new File(getActivity().getExternalCacheDir(),"temp-img.jpg");
            changeComplainImage();
        }
    }
    private void onSubmit() {
        callBacks.onImageSubmit();
    }
    void changeComplainImage()
    {

        List<Intent> otherImageCaptureIntents=new ArrayList<>();
        List<ResolveInfo> otherImageCaptureActivities=getActivity().getPackageManager()
                .queryIntentActivities(new Intent(MediaStore.ACTION_IMAGE_CAPTURE),0);
        for(ResolveInfo info: otherImageCaptureActivities) {
            Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            captureIntent.setClassName(info.activityInfo.packageName, info.activityInfo.name);
            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempOutputFile));
            otherImageCaptureIntents.add(captureIntent);
        }
        Intent selectImageIntent=new Intent(Intent.ACTION_PICK);
        selectImageIntent.setType("image/*");
        Intent chooser=Intent.createChooser(selectImageIntent,"Choose Avatar");
        chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS
                ,otherImageCaptureIntents
                        .toArray(new Parcelable[otherImageCaptureActivities.size()]));
        startActivityForResult(chooser,REQUEST_SELECT_IMAGE);

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        if(resultCode!=RESULT_OK)
        {
            tempOutputFile.delete();
            return;
        }
        if(requestCode==REQUEST_SELECT_IMAGE)
        {
            Uri outputFile;
            Uri tempFileUri=Uri.fromFile(tempOutputFile);
            if(data!=null&&(data.getAction()==null||!data.getAction().equals(MediaStore.ACTION_IMAGE_CAPTURE)))
            {
                outputFile=data.getData();
            }
            else
                outputFile=tempFileUri;
            callBacks.getImageCrop(outputFile,tempFileUri,tempOutputFile,R.id.fragment_via_image_image);


        }
        else if(requestCode==Crop.REQUEST_CROP)
        {
            //todo send tempfileuri to server as new Avatar
            complainImage.setImageResource(0);
            Log.e("yess","coming");
            croppedUri=Uri.fromFile(tempOutputFile);
            complainImage.setImageURI(Uri.fromFile(tempOutputFile));
        }

    }




}
