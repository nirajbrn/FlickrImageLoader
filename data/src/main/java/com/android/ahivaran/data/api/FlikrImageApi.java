package com.android.ahivaran.data.api;


import com.android.ahivaran.data.entity.ResponseEntity;

import java.util.Map;

import retrofit.http.GET;
import retrofit.http.QueryMap;
import rx.Observable;

public interface FlikrImageApi {
    @GET(Urls.FLICKR_IMAGE_API)
    Observable<ResponseEntity> getImageUrls(@QueryMap Map<String, String > filters);

}
