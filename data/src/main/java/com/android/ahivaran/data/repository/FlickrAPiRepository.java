package com.android.ahivaran.data.repository;


import com.android.ahivaran.data.api.FlikrImageApi;
import com.android.ahivaran.data.api.Urls;
import com.android.ahivaran.data.entity.ResponseEntity;
import com.android.ahivaran.data.mapper.PhotoEntityMapper;
import com.android.ahivaran.domain.Photos;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.functions.Func1;

public final class FlickrAPiRepository implements com.android.ahivaran.domain.repository.FlickrAPiRepository {
    private FlikrImageApi imageApi;

    public FlickrAPiRepository(FlikrImageApi imageApi) {
        this.imageApi = imageApi;
    }

    @Override
    public Observable<Photos> getFlickrImageData(int pageNo) {
        Map<String, String> filters = new HashMap<>();
        filters.put("api_key", Urls.API_KEY);
        filters.put("user_id", Urls.USER_ID);
        filters.put("method", "flickr.people.getPublicPhotos");
        filters.put("format", "json");
        filters.put("nojsoncallback", "1");
        filters.put("page", String.valueOf(pageNo));
        filters.put("per_page", "10");
        return imageApi.getImageUrls(filters).map(new Func1<ResponseEntity, Photos>() {
            @Override
            public Photos call(ResponseEntity responseEntity) {
                return new PhotoEntityMapper().transform(responseEntity.getPhotos());
            }
        });
    }
}
