package com.example.admininternationalconnect.domain.repository;

import com.example.admininternationalconnect.domain.util.OnDataFetchedListener;
import com.google.firebase.database.DatabaseReference;

public interface AdminRepository {

    void getAdminInfo(DatabaseReference adminNode, final OnDataFetchedListener onDataFetchedListener);

}
