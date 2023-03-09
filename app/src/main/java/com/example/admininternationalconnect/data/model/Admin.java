package com.example.admininternationalconnect.data.model;

public class Admin {

    String mUserName;
    String mPassword;

    public Admin(String userName, String password) {
        mUserName = userName;
        mPassword = password;
    }

    public Admin() {
    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }
}
