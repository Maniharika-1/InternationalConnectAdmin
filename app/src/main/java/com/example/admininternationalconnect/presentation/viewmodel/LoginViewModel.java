package com.example.admininternationalconnect.presentation.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.admininternationalconnect.AdminBaseClass;
import com.example.admininternationalconnect.data.model.Admin;
import com.example.admininternationalconnect.domain.usecase.LoginUseCase;
import com.example.admininternationalconnect.domain.util.GetDataListener;
import com.example.admininternationalconnect.presentation.util.SharedPreferencesUtil;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginViewModel extends AndroidViewModel {

    LoginUseCase loginUseCase;
    DatabaseReference adminNode;
    public MutableLiveData<String> errorMessage;
    public MutableLiveData<Boolean> loginSuccessful;
    SharedPreferencesUtil sharedPreferencesUtil;
    final String TAG = "LoginViewModel";

    public LoginViewModel(Application application, LoginUseCase loginUseCase) {
        super(application);
        this.loginUseCase = loginUseCase;
        adminNode = FirebaseDatabase.getInstance().getReference().child("admin");
        errorMessage = new MutableLiveData<>();
        loginSuccessful = new MutableLiveData<>();
        errorMessage.setValue(null);
        //loginSuccessful.setValue(false);
        sharedPreferencesUtil = new SharedPreferencesUtil();
    }

    public void login(String userName, String password) {
        loginUseCase.execute(adminNode, new GetDataListener() {
            @Override
            public void onSuccess(Admin admin) {
                if (admin != null && admin.getUserName() != null && admin.getUserName().contentEquals(userName)
                        && admin.getPassword() != null && admin.getPassword().contentEquals(password)) {

                    loginSuccessful.setValue(true);
                    errorMessage.setValue(null);

                } else {

                    loginSuccessful.setValue(false);
                    errorMessage.setValue("User name or password is incorrect!");

                }
            }

            @Override
            public void onStart() {
            }

            @Override
            public void onFailure() {
                loginSuccessful.setValue(false);
                errorMessage.setValue("User name or password is incorrect!");
            }
        });
    }

}
