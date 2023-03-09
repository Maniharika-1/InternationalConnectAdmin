package com.example.admininternationalconnect.presentation.util;

import android.content.SharedPreferences;

import com.example.admininternationalconnect.AdminBaseClass;

public class SharedPreferencesUtil {

    SharedPreferences mSharedPreferences;

    public SharedPreferencesUtil() {
        mSharedPreferences = AdminBaseClass.mSharedPreferences;
    }

    public void putBoolean(String key, Boolean value) {
        mSharedPreferences.edit().putBoolean(key, value).apply();
    }

    public Boolean getBoolean(String key, Boolean defaultValue) {
        return mSharedPreferences.getBoolean(key, defaultValue);
    }
}
