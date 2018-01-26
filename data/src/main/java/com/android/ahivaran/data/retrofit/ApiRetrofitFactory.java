package com.android.ahivaran.data.retrofit;


import com.android.ahivaran.data.api.Urls;

import retrofit.RestAdapter;

public final class ApiRetrofitFactory {

    private static RestAdapter instance;

    public static void build() {
        if (instance == null) {
            instance = new RestAdapter.Builder().setEndpoint(Urls.getBaseUrl())
                    .setConverter(ConverterFactory.create())
                    .build();
        }
    }

    public static RestAdapter getInstance() {
        if (instance == null) {
            throw new RuntimeException("Call build() before accessing the instance!!");
        }
        return instance;
    }

}
