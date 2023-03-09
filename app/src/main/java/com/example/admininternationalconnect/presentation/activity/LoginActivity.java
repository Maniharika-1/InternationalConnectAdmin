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

    ActivityLoginBinding mActivityLoginBinding;
    LoginViewModel mLoginViewModel;
    LoginViewModelFactory mLoginViewModelFactory;
    final String LOGGED_IN_KEY = "LoggedIn";
    final String TAG = "LoginActivity";
    private SharedPreferencesUtil mSharedPreferencesUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);

        mSharedPreferencesUtil = new SharedPreferencesUtil();
        final Boolean[] loggedIn = {false};

        final View content = findViewById(android.R.id.content);
        content.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {

                        loggedIn[0] = mSharedPreferencesUtil.getBoolean(LOGGED_IN_KEY, false);

                        Log.d(TAG, "onPreDraw: " + loggedIn[0]);
                        if (loggedIn[0]) {

                            callHomeActivity();

                        } else {

                            mActivityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
                            setContentView(mActivityLoginBinding.getRoot());

                            initLoginViewModelAndViewModelFactory();

                            mActivityLoginBinding.setLoginViewModel(mLoginViewModel);
                            mActivityLoginBinding.setLifecycleOwner(LoginActivity.this);

                            initClickListeners();

                            setLoginSuccessfulLiveDataObserver();

                        }

                        content.getViewTreeObserver().removeOnPreDrawListener(this);
                        return true;
                    }
                });
    }

    private void setLoginSuccessfulLiveDataObserver() {

        mLoginViewModel.mLoginSuccessful.observe(LoginActivity.this, loginSuccessful -> {

            if (loginSuccessful) {

                mSharedPreferencesUtil.putBoolean(LOGGED_IN_KEY, true);
                callHomeActivity();

            } else {

                mSharedPreferencesUtil.putBoolean(LOGGED_IN_KEY, false);

            }

        });

    }

    private void initClickListeners() {

        mActivityLoginBinding.loginBtn.setOnClickListener(view -> {

            mLoginViewModel.login(
                    mActivityLoginBinding.userNameET.getText().toString(),
                    mActivityLoginBinding.passwordET.getText().toString()
            );

        });

    }

    private void initLoginViewModelAndViewModelFactory() {

        mLoginViewModelFactory = new LoginViewModelFactory(getApplication(), new LoginUseCase(new AdminRepositoryImpl(new AdminDataSourceImpl())));

        mLoginViewModel = new ViewModelProvider(LoginActivity.this, mLoginViewModelFactory).get(LoginViewModel.class);

    }

    private void callHomeActivity() {

        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }
}