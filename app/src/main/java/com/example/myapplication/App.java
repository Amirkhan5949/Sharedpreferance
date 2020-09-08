package com.example.myapplication;

import android.app.Application;

import com.example.myapplication.SharedPrefsManager;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SharedPrefsManager.initialize(this);
    }
}
