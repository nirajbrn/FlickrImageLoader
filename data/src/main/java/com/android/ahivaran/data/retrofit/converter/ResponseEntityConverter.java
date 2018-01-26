package com.android.ahivaran.data.retrofit.converter;


import com.android.ahivaran.data.entity.ResponseEntity;
import com.android.ahivaran.data.gson.GsonFactory;

import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Type;

import retrofit.converter.ConversionException;
import retrofit.mime.TypedInput;

public class ResponseEntityConverter extends BaseConverter<ResponseEntity> {

    @Override
    public ResponseEntity fromBody(TypedInput body, Type type) throws ConversionException {
        try {
            final String bodyString = getBody(body);
            final ResponseEntity responseEntity =
                    GsonFactory.getInstance().fromJson(bodyString, ResponseEntity.class);
            return responseEntity;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected ResponseEntity parseEntity(String entityJson) throws JSONException {
        return null;
    }

    @Override
    protected String convertToBody(ResponseEntity object) throws JSONException {
        throw new RuntimeException("Not implemented");
    }
}
