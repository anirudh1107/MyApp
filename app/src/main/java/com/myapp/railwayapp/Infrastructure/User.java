package com.myapp.railwayapp.Infrastructure;


public class User {
    private String userName;
    private boolean isLoggedIn;
    private int id;
    public User()
    {
        isLoggedIn=false;
    }
    public String getUserName() {
        return userName;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
}
