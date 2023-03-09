package com.example.admininternationalconnect.domain.util;

import com.example.admininternationalconnect.data.model.Admin;

public interface OnDataFetchedListener {

    void onSuccess(Admin admin);
    void onStart();
    void onFailure();

}
