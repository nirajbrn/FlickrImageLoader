package com.android.ahivaran.data.retrofit;


import android.util.Log;

import com.android.ahivaran.data.entity.ResponseEntity;
import com.android.ahivaran.data.retrofit.converter.ResponseEntityConverter;

import java.lang.reflect.Type;
import java.util.HashMap;

import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

public final class ConverterFactory implements Converter {
    private static final String TAG = ConverterFactory.class.getSimpleName();
    private HashMap<Type, Converter> mConverters;

    private ConverterFactory() {
        mConverters = new HashMap<>();
    }

    public static ConverterFactory create() {
        return new ConverterFactory();
    }

    private Converter get(final Type type) {
        if (mConverters.containsKey(type)) return mConverters.get(type);
        Converter converter = null;

        if (((Class) type).getCanonicalName()
                .contentEquals(ResponseEntity.class.getCanonicalName())) {
            converter = new ResponseEntityConverter();
        }
        if (converter == null) throw new RuntimeException("This should not happen");
        mConverters.put(type, converter);
        return converter;
    }

    @Override
    public Object fromBody(TypedInput body, Type type) throws ConversionException {
        Log.d(TAG, "from with " + body.toString() + " " + type.toString());
        return get(type).fromBody(body, type);
    }

    @Override
    public TypedOutput toBody(Object object) {
        Log.d(TAG, "toBody with " + object.toString() + " " + object.getClass().toString());
        return get(object.getClass()).toBody(object);
    }
}
