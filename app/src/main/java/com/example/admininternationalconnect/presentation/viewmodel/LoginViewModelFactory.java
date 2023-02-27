package com.example.admininternationalconnect.presentation.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.admininternationalconnect.domain.usecase.LoginUseCase;

public class LoginViewModelFactory implements ViewModelProvider.Factory {

    private LoginUseCase loginUseCase;

    public LoginViewModelFactory(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(LoginViewModel.class))
            return (T) new LoginViewModel(loginUseCase);

        return ViewModelProvider.Factory.super.create(modelClass);
    }
}
