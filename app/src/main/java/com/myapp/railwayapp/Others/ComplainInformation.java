package com.myapp.railwayapp.Others;

/**
 * Created by naman on 11/9/17.
 */

public class ComplainInformation {
    private String complainType;
    private String location;
    private String description;
    private String mobileNumber;
    private String complainTypeDetail;

    public void setComplainTypeDetail(String complainTypeDetail) {
        this.complainTypeDetail = complainTypeDetail;
    }


    public String getComplainTypeDetail() {

        return complainTypeDetail;
    }

    private int status;

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {

        return status;
    }

    public String getComplainType() {
        return complainType;
    }

    public String getLocation() {
        return location;
    }

    public void setComplainType(String complainType) {
        this.complainType = complainType;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getDescription() {

        return description;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }
}
