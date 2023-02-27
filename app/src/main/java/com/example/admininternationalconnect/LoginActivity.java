package com.example.admininternationalconnect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.admininternationalconnect.data.repository.datasourceImpl.AdminDataSourceImpl;
import com.example.admininternationalconnect.databinding.ActivityLoginBinding;
import com.example.admininternationalconnect.domain.repository.AdminRepositoryImpl;
import com.example.admininternationalconnect.domain.usecase.LoginUseCase;
import com.example.admininternationalconnect.presentation.viewmodel.LoginViewModel;
import com.example.admininternationalconnect.presentation.viewmodel.LoginViewModelFactory;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding activityLoginBinding;
    LoginViewModel loginViewModel;
    LoginViewModelFactory loginViewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        activityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(activityLoginBinding.getRoot());

        loginViewModelFactory = new LoginViewModelFactory(new LoginUseCase(new AdminRepositoryImpl(new AdminDataSourceImpl())));

        loginViewModel = new ViewModelProvider(this, loginViewModelFactory).get(LoginViewModel.class);

        activityLoginBinding.setLoginViewModel(loginViewModel);
        activityLoginBinding.setLifecycleOwner(this);

        activityLoginBinding.loginBtn.setOnClickListener(view -> {

            loginViewModel.login(
                    activityLoginBinding.userNameET.getText().toString(),
                    activityLoginBinding.passwordET.getText().toString()
            );

        });
    }
}