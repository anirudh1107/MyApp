package com.myapp.railwayapp.Infrastructure;

/**
 * Created by dellpc on 12/1/2017.
 */

public class userDetail {
    private String Username;
    private String Mobile;
    private String Address;
    private String Locality;

    public userDetail() {

    }

    public userDetail(String username, String mobile, String address, String locality) {
        Username = username;
        Mobile = mobile;
        Address = address;
        Locality = locality;
    }

    public String getLocality() {
        return Locality;
    }

    public void setLocality(String locality) {
        Locality = locality;
    }

    public String getUsername() {
        return Username;
    }

    public String getMobile() {
        return Mobile;
    }

    public String getAddress() {
        return Address;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
