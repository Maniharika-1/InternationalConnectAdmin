package com.example.admininternationalconnect.domain.repository;

import com.example.admininternationalconnect.data.repository.datasource.AdminDataSource;
import com.example.admininternationalconnect.domain.util.OnDataFetchedListener;
import com.google.firebase.database.DatabaseReference;

public class AdminRepositoryImpl implements AdminRepository{

    AdminDataSource mAdminDataSource;

    public AdminRepositoryImpl(AdminDataSource adminDataSource) {
        mAdminDataSource = adminDataSource;
    }

    @Override
    public void getAdminInfo(DatabaseReference adminNode, final OnDataFetchedListener onDataFetchedListener) {
        mAdminDataSource.getAdminInfo(adminNode, onDataFetchedListener);
    }
}
