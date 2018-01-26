package com.android.ahivaran;


import android.app.Application;

import com.android.ahivaran.data.gson.GsonFactory;
import com.android.ahivaran.data.retrofit.ApiRetrofitFactory;

public class LoaderApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApiRetrofitFactory.build();
        GsonFactory.build();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
