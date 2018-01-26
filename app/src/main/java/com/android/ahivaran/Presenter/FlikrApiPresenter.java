package com.android.ahivaran.Presenter;


import com.android.ahivaran.domain.FlickrImage;
import com.android.ahivaran.domain.Photos;
import com.android.ahivaran.domain.interactor.GetImageData;
import com.android.ahivaran.domain.repository.FlickrAPiRepository;
import com.android.ahivaran.utils.CommonSubscriber;

public final class FlikrApiPresenter extends Presenter{

    private FlickrAPiRepository flickrAPiRepository;

    public FlikrApiPresenter(FlickrAPiRepository flickrAPiRepository) {
        this.flickrAPiRepository = flickrAPiRepository;
    }

    public void getImagesFromFlickr(int pageNo, CommonSubscriber<Photos> subscriber) {
        executeUseCase(new GetImageData(mThreadExecutor, mPostExecutorThread, flickrAPiRepository, pageNo), subscriber);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        unsubscribe();
    }
}
