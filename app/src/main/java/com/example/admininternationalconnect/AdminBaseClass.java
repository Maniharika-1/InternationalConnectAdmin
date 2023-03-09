package com.example.admininternationalconnect;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

public class AdminBaseClass extends Application {

    public static SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseApp.initializeApp(this);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        sharedPreferences = getApplicationContext().getSharedPreferences(getApplicationContext().getString(R.string.shared_preferences_name), Context.MODE_PRIVATE);
    }
}
