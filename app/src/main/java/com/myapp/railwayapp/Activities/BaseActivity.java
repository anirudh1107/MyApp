package com.myapp.railwayapp.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.myapp.railwayapp.Infrastructure.Auth;
import com.myapp.railwayapp.Infrastructure.RailwayApplication;

/**
 * Created by naman on 11/9/17.
 */

public class BaseActivity extends AppCompatActivity {
    protected static RailwayApplication application;
    protected FirebaseAuth mAuth;
    protected EditText userName, password;
    protected FirebaseAuth.AuthStateListener mAuthListener;
    protected FirebaseUser user;



    protected SharedPreferences sharedPreferences;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences=getSharedPreferences("LoginData", Context.MODE_PRIVATE);
        application= (RailwayApplication) getApplication();
        mAuth = FirebaseAuth.getInstance();
    }
}
