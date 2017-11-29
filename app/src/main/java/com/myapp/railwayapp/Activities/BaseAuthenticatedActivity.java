package com.myapp.railwayapp.Activities;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;


public class BaseAuthenticatedActivity extends BaseActivity {
    private String uname, pword;
    private final String DEFAULT = "N/A";
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!application.getAuth().getUser().isLoggedIn())
        {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }
}