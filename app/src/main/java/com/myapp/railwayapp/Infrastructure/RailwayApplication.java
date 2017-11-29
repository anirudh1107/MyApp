package com.myapp.railwayapp.Infrastructure;

import android.app.Application;


public class RailwayApplication extends Application {
    private Auth auth;
    @Override
    public void onCreate()
    {
       super.onCreate();
        auth=new Auth(this);
    }
    public Auth getAuth() {
        return auth;
    }
}
