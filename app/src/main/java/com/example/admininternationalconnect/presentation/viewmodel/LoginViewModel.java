package com.example.admininternationalconnect.presentation.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.admininternationalconnect.data.model.Admin;
import com.example.admininternationalconnect.domain.usecase.LoginUseCase;
import com.example.admininternationalconnect.domain.util.OnDataFetchedListener;
import com.example.admininternationalconnect.presentation.util.SharedPreferencesUtil;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginViewModel extends AndroidViewModel {

    LoginUseCase mLoginUseCase;
    DatabaseReference mAdminNode;
    public MutableLiveData<String> mErrorMessage;
    public MutableLiveData<Boolean> mLoginSuccessful;
    SharedPreferencesUtil mSharedPreferencesUtil;
    final String TAG = "LoginViewModel";

    public LoginViewModel(Application application, LoginUseCase loginUseCase) {
        super(application);

        mLoginUseCase = loginUseCase;
        mAdminNode = FirebaseDatabase.getInstance().getReference().child("admin");
        mErrorMessage = new MutableLiveData<>();
        mLoginSuccessful = new MutableLiveData<>();
        mErrorMessage.setValue(null);
        //loginSuccessful.setValue(false);
        mSharedPreferencesUtil = new SharedPreferencesUtil();
    }

    public void login(String userName, String password) {
        mLoginUseCase.execute(mAdminNode, new OnDataFetchedListener() {
            @Override
            public void onSuccess(Admin admin) {
                if (admin != null && admin.getmUserName() != null && admin.getmUserName().contentEquals(userName)
                        && admin.getmPassword() != null && admin.getmPassword().contentEquals(password)) {

                    mLoginSuccessful.setValue(true);
                    mErrorMessage.setValue(null);

                } else {

                    mLoginSuccessful.setValue(false);
                    mErrorMessage.setValue("User name or password is incorrect!");

                }
            }

            @Override
            public void onStart() {
            }

            @Override
            public void onFailure() {
                mLoginSuccessful.setValue(false);
                mErrorMessage.setValue("User name or password is incorrect!");
            }
        });
    }

}
