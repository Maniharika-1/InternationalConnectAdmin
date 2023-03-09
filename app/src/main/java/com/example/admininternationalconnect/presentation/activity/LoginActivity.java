package com.example.admininternationalconnect.presentation.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import com.example.admininternationalconnect.data.repository.datasourceImpl.AdminDataSourceImpl;
import com.example.admininternationalconnect.databinding.ActivityLoginBinding;
import com.example.admininternationalconnect.domain.repository.AdminRepositoryImpl;
import com.example.admininternationalconnect.domain.usecase.LoginUseCase;
import com.example.admininternationalconnect.presentation.util.SharedPreferencesUtil;
import com.example.admininternationalconnect.presentation.viewmodel.LoginViewModel;
import com.example.admininternationalconnect.presentation.viewmodelfactory.LoginViewModelFactory;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding activityLoginBinding;
    LoginViewModel loginViewModel;
    LoginViewModelFactory loginViewModelFactory;
    final String LOGGED_IN_KEY = "LoggedIn";
    final String TAG = "LoginActivity";
    private SharedPreferencesUtil sharedPreferencesUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);

        sharedPreferencesUtil = new SharedPreferencesUtil();
        final Boolean[] loggedIn = {false};

        final View content = findViewById(android.R.id.content);
        content.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {

                        loggedIn[0] = sharedPreferencesUtil.getBoolean(LOGGED_IN_KEY, false);

                        Log.d(TAG, "onPreDraw: " + loggedIn[0]);
                        if (loggedIn[0]) {

                            callHomeActivity();

                        } else {

                            activityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
                            setContentView(activityLoginBinding.getRoot());

                            initLoginViewModelAndViewModelFactory();

                            activityLoginBinding.setLoginViewModel(loginViewModel);
                            activityLoginBinding.setLifecycleOwner(LoginActivity.this);

                            initClickListeners();

                            setLoginSuccessfulLiveDataObserver();

                        }

                        content.getViewTreeObserver().removeOnPreDrawListener(this);
                        return true;
                    }
                });
    }

    private void setLoginSuccessfulLiveDataObserver() {

        loginViewModel.loginSuccessful.observe(LoginActivity.this, loginSuccessful -> {

            if (loginSuccessful) {

                sharedPreferencesUtil.putBoolean(LOGGED_IN_KEY, true);
                callHomeActivity();

            } else {

                sharedPreferencesUtil.putBoolean(LOGGED_IN_KEY, false);

            }

        });

    }

    private void initClickListeners() {

        activityLoginBinding.loginBtn.setOnClickListener(view -> {

            loginViewModel.login(
                    activityLoginBinding.userNameET.getText().toString(),
                    activityLoginBinding.passwordET.getText().toString()
            );

        });

    }

    private void initLoginViewModelAndViewModelFactory() {

        loginViewModelFactory = new LoginViewModelFactory(getApplication(), new LoginUseCase(new AdminRepositoryImpl(new AdminDataSourceImpl())));

        loginViewModel = new ViewModelProvider(LoginActivity.this, loginViewModelFactory).get(LoginViewModel.class);

    }

    private void callHomeActivity() {

        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }
}