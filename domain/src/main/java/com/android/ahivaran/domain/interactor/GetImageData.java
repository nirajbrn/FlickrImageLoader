package com.android.ahivaran.domain.interactor;


import com.android.ahivaran.domain.Photos;
import com.android.ahivaran.domain.executor.PostExecutorThread;
import com.android.ahivaran.domain.executor.ThreadExecutor;
import com.android.ahivaran.domain.repository.FlickrAPiRepository;

import rx.Observable;

public final class GetImageData extends UseCase<Photos>{


    private FlickrAPiRepository flickrAPiRepository;
    private int pageNo;

    public GetImageData(ThreadExecutor threadExecutor, PostExecutorThread postExecutionThread,
                        FlickrAPiRepository flickrAPiRepository, int pageNo) {
        super(threadExecutor, postExecutionThread);
        this.flickrAPiRepository = flickrAPiRepository;
        this.pageNo = pageNo;
    }

    @Override
    protected Observable<Photos> buildUseCaseObservable() {
        return flickrAPiRepository.getFlickrImageData(pageNo);
    }
}
