package com.example.jengtallis.simpleui;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by alog1024 on 8/16/16.
 */
public class SimpleUIApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("L1cDw8RWCFuZKTW1RaDSMoMZm9gyDMqy1NHeNnKx")
                .server("https://parseapi.back4app.com/")
                .clientKey("doMRyB0H0cum2ZfxGBPF96XZ7paMpFa7xqUJEVuo")
                .build()
        );
    }
}
