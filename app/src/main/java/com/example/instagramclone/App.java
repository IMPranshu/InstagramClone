package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("nGV91cBglSQr30qfiioZZIz7o00Ar3JJ9oWih59L")
                // if defined
                .clientKey("vMxYmZLULx6iMk7L6NqV7RUeZbnxEDCSAAwMSs7d")
                .server("https://parseapi.back4app.com/")
                .build()
        );


    }
}
