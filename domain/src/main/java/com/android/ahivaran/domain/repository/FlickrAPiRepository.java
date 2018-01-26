package com.android.ahivaran.domain.repository;


import com.android.ahivaran.domain.Photos;

import rx.Observable;

public interface FlickrAPiRepository {
    Observable<Photos> getFlickrImageData(int pageNo);
}
