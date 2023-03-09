package com.example.admininternationalconnect.presentation.viewmodelfactory;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.admininternationalconnect.domain.usecase.LoginUseCase;
import com.example.admininternationalconnect.presentation.viewmodel.LoginViewModel;

public class LoginViewModelFactory implements ViewModelProvider.Factory {

    private LoginUseCase mLoginUseCase;
    private Application mApplication;

    public LoginViewModelFactory(Application application, LoginUseCase loginUseCase) {
        mApplication = application;
        mLoginUseCase = loginUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(LoginViewModel.class))
            return (T) new LoginViewModel(mApplication, mLoginUseCase);

        return ViewModelProvider.Factory.super.create(modelClass);
    }
}
