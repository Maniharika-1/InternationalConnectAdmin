package com.example.admininternationalconnect.domain.repository;

import com.example.admininternationalconnect.data.repository.datasource.AdminDataSource;
import com.example.admininternationalconnect.data.repository.datasourceImpl.AdminDataSourceImpl;
import com.example.admininternationalconnect.domain.util.GetDataListener;
import com.google.firebase.database.DatabaseReference;

public class AdminRepositoryImpl implements AdminRepository{

    AdminDataSource adminDataSource;
    public AdminRepositoryImpl(AdminDataSource adminDataSource) {
        this.adminDataSource = adminDataSource;
    }

    @Override
    public void getAdminInfo(DatabaseReference adminNode, final GetDataListener getDataListener) {
        adminDataSource.getAdminInfo(adminNode, getDataListener);
    }
}
