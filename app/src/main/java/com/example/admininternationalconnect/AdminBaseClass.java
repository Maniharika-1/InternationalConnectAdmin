package com.example.admininternationalconnect;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

public class AdminBaseClass extends Application {

    public static SharedPreferences mSharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseApp.initializeApp(this);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        mSharedPreferences = getApplicationContext().getSharedPreferences(getApplicationContext().getString(R.string.shared_preferences_name), Context.MODE_PRIVATE);
    }
}
