package com.example.admininternationalconnect.domain.repository;

import com.example.admininternationalconnect.domain.util.GetDataListener;
import com.google.firebase.database.DatabaseReference;

public interface AdminRepository {

    void getAdminInfo(DatabaseReference adminNode, final GetDataListener getDataListener);

}
