package com.example.admininternationalconnect.data.repository.datasourceImpl;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.admininternationalconnect.data.model.Admin;
import com.example.admininternationalconnect.data.repository.datasource.AdminDataSource;
import com.example.admininternationalconnect.domain.util.GetDataListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class AdminDataSourceImpl implements AdminDataSource {

    private String TAG = "AdminDataSourceImpl";

    @Override
    public void getAdminInfo(DatabaseReference adminNode, final GetDataListener getDataListener) {

        getDataListener.onStart();
        adminNode.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Admin admin = snapshot.getValue(Admin.class);
                    getDataListener.onSuccess(admin);
                } else getDataListener.onFailure();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "onCancelled: " + error.getMessage());
                getDataListener.onFailure();
            }
        });

    }

}
