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
}
