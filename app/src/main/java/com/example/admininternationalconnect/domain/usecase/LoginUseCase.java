package com.example.admininternationalconnect.domain.usecase;

import com.example.admininternationalconnect.domain.repository.AdminRepository;
import com.example.admininternationalconnect.domain.util.OnDataFetchedListener;
import com.google.firebase.database.DatabaseReference;

public class LoginUseCase {

    AdminRepository mAdminRepository;

    public LoginUseCase(AdminRepository adminRepository) {
        mAdminRepository = adminRepository;
    }

    public void execute(DatabaseReference adminNode, final OnDataFetchedListener onDataFetchedListener) {
        mAdminRepository.getAdminInfo(adminNode, onDataFetchedListener);
    }
}
