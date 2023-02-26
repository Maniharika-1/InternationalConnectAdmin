package com.example.admininternationalconnect.domain.usecase;

import com.example.admininternationalconnect.domain.repository.AdminRepository;
import com.example.admininternationalconnect.domain.util.GetDataListener;
import com.google.firebase.database.DatabaseReference;

public class LoginUseCase {

    AdminRepository adminRepository;
    public LoginUseCase(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    void execute(DatabaseReference adminNode, final GetDataListener getDataListener) {
        adminRepository.getAdminInfo(adminNode, getDataListener);
    }
}
