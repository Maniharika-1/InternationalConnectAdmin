package com.example.admininternationalconnect.presentation.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.admininternationalconnect.domain.usecase.LoginUseCase;

public class LoginViewModelFactory implements ViewModelProvider.Factory {

    private LoginUseCase loginUseCase;
    private Application application;

    public LoginViewModelFactory(Application application, LoginUseCase loginUseCase) {
        this.application = application;
        this.loginUseCase = loginUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(LoginViewModel.class))
            return (T) new LoginViewModel(application, loginUseCase);

        return ViewModelProvider.Factory.super.create(modelClass);
    }
}
