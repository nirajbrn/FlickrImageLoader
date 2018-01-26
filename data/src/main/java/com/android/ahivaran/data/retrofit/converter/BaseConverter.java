package com.android.ahivaran.data.retrofit.converter;


import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

public abstract class BaseConverter<T> implements Converter {

    private static final String TAG = BaseConverter.class.getSimpleName();
    private static final String MIME_TYPE = "application/json; charset=UTF-8";

    @Override
    public T fromBody(TypedInput body, Type type) throws ConversionException {
        return null;
    }

    /**
     * To parse the specific JSON string related with {@link T}
     *
     * @param entityJson entity specific JSON string block
     * @return instance of {@link T}
     * @throws JSONException
     */
    abstract protected T parseEntity(final String entityJson) throws JSONException;

    /**
     * Convert to body
     *
     * @param object Object
     * @throws JSONException
     */
    abstract protected String convertToBody(T object) throws JSONException;

    /**
     * Convert an object to an appropriate representation for HTTP transport.
     *
     * @param object Object instance to convert.
     * @return Representation of the specified object as bytes.
     */
    @Override public TypedOutput toBody(Object object) {
        try {
            return new TypedByteArray(MIME_TYPE, convertToBody((T) object).getBytes());
        } catch (JSONException e) {
            throw new AssertionError(e);
        }
    }

    protected String getBody(final TypedInput typedInput) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(typedInput.in()));
        String read;
        while ((read = br.readLine()) != null) {
            //System.out.println(read);
            sb.append(read);
        }
        br.close();
        return sb.toString();
    }
}
