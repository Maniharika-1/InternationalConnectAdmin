package com.example.admininternationalconnect.presentation.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.admininternationalconnect.data.model.Admin;
import com.example.admininternationalconnect.domain.usecase.LoginUseCase;
import com.example.admininternationalconnect.domain.util.GetDataListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginViewModel extends ViewModel {

    LoginUseCase loginUseCase;
    DatabaseReference adminNode;

    public LoginViewModel(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
        adminNode = FirebaseDatabase.getInstance().getReference().child("admin");
    }

    public void login(String userName, String password) {
        loginUseCase.execute(adminNode, new GetDataListener() {
            @Override
            public void onSuccess(Admin admin) {
                if (admin != null && admin.getUserName() != null && admin.getUserName().contentEquals(userName)
                        && admin.getPassword() != null && admin.getPassword().contentEquals(password)) {
                    //call intent
                } else {

                }
            }

            @Override
            public void onStart() {

            }

            @Override
            public void onFailure() {

            }
        });
    }
}
