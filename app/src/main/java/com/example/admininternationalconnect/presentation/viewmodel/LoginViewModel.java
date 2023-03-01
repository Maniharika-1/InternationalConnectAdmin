package com.example.admininternationalconnect.presentation.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.admininternationalconnect.R;
import com.example.admininternationalconnect.data.model.Admin;
import com.example.admininternationalconnect.domain.usecase.LoginUseCase;
import com.example.admininternationalconnect.domain.util.GetDataListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginViewModel extends AndroidViewModel {

    LoginUseCase loginUseCase;
    DatabaseReference adminNode;
    public MutableLiveData<String> errorMessage;
    Boolean loginSuccessful;
    SharedPreferences sharedPreferences;
    final String TAG = "LoginViewModel";

    public LoginViewModel(Application application, LoginUseCase loginUseCase) {
        super(application);
        this.loginUseCase = loginUseCase;
        adminNode = FirebaseDatabase.getInstance().getReference().child("admin");
        errorMessage = new MutableLiveData<>();
        errorMessage.setValue(null);
        loginSuccessful = true;
        sharedPreferences = getApplication().getSharedPreferences(getApplication().getString(R.string.shared_preferences_name), Context.MODE_PRIVATE);
    }

    public void login(String userName, String password) {

        loginUseCase.execute(adminNode, new GetDataListener() {
            @Override
            public void onSuccess(Admin admin) {

                if (admin != null && admin.getUserName() != null && admin.getUserName().contentEquals(userName)
                        && admin.getPassword() != null && admin.getPassword().contentEquals(password)) {
                    //call intent
                    loginSuccessful = true;
                    errorMessage.setValue(null);
                    sharedPreferences.edit().putBoolean("LoggedIn", true).apply();

                } else {
                    loginSuccessful = false;
                    errorMessage.setValue("User name or password is incorrect!");
                    sharedPreferences.edit().putBoolean("LoggedIn", false).apply();
                }
            }

            @Override
            public void onStart() {
            }

            @Override
            public void onFailure() {
                loginSuccessful = false;
                errorMessage.setValue("User name or password is incorrect!");
                sharedPreferences.edit().putBoolean("LoggedIn", false).apply();
            }
        });
    }
}
