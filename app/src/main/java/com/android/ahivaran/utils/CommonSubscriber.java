package com.android.ahivaran.utils;


import android.util.Log;

import rx.Subscriber;

public abstract class CommonSubscriber<T> extends Subscriber<T> {
    private static final String TAG = CommonSubscriber.class.getSimpleName();

    @Override
    public void onCompleted() {
        Log.d(TAG, "onCompleted():");
    }

    @Override
    public void onError(Throwable e) {
        Log.d(TAG, "onError(): "+e.getMessage());
    }

    @Override
    public void onNext(T t) {

    }
}
