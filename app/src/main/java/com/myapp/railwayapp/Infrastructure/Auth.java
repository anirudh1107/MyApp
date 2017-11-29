package com.myapp.railwayapp.Infrastructure;

import android.content.Context;

import com.myapp.railwayapp.Infrastructure.User;

public class Auth {
    protected Context context;
    protected User user;

    public Auth(Context context) {
        this.context = context;
        user=new User();
    }

    public User getUser() {
        return user;
    }
}
