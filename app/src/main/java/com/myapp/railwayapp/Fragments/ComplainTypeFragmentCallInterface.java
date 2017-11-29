package com.myapp.railwayapp.Fragments;

import android.net.Uri;
import android.view.View;

import java.io.File;

public interface ComplainTypeFragmentCallInterface {
    void onComplainTypeClickBack();
    void onComplainTypeClickSubmit();
    void getImageCrop(Uri outputFile, Uri tempFileUri,File tempOutputfile,int id);
    void onImageSubmit();
}
