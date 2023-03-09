package com.example.admininternationalconnect.data.repository.datasource;

import com.example.admininternationalconnect.domain.util.OnDataFetchedListener;
import com.google.firebase.database.DatabaseReference;

public interface AdminDataSource {

    void getAdminInfo(DatabaseReference adminNode, final OnDataFetchedListener onDataFetchedListener);

}
