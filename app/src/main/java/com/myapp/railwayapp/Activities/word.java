package com.myapp.railwayapp.Activities;

/**
 * Created by dellpc on 11/15/2017.
 */

public class word {
    private String Description;
    private String Location;
    private String MobNumber;
    private int Status;
    private String Type;
    private String Uid;
    private String key;
    private String TypeDetail;
    private String cid;
    private String imageUID;

    public word() {
    }

    public word(String description, String location, String mobNumber,int Status, String type,String uid,String typeDetail,String Cid,String imageuid) {
        Description = description;
        Location = location;
        MobNumber = mobNumber;
        this.Status=Status;
        Type = type;
        Uid=uid;
        TypeDetail=typeDetail;
        cid=Cid;
        imageUID=imageuid;

    }

    public String getDescription() {
        return Description;
    }

    public String getLocation() {
        return Location;
    }

    public String getMobNumber() {
        return MobNumber;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getType() {
        return Type;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public void setMobNumber(String mobNumber) {
        MobNumber = mobNumber;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        this.Status = status;
    }

    public String getTypeDetail() {
        return TypeDetail;
    }

    public void setTypeDetail(String typeDetail) {
        TypeDetail = typeDetail;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getImageUID() {
        return imageUID;
    }

    public void setImageUID(String imageUID) {
        this.imageUID = imageUID;
    }
}
