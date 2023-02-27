package com.example.admininternationalconnect.domain.util;

import com.example.admininternationalconnect.data.model.Admin;

public interface GetDataListener {

    void onSuccess(Admin admin);
    void onStart();
    void onFailure();

}
