package com.android.ahivaran.domain.interactor;


import com.android.ahivaran.domain.executor.PostExecutorThread;
import com.android.ahivaran.domain.executor.ThreadExecutor;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

public abstract class UseCase<T> {

    private ThreadExecutor mThreadExecutor;
    private PostExecutorThread mPostExecutorThread;

    private Subscription subscription = Subscriptions.empty();


    protected UseCase(final ThreadExecutor threadExecutor,
                      final PostExecutorThread postExecutionThread) {
        mThreadExecutor = threadExecutor;
        mPostExecutorThread = postExecutionThread;
    }

    protected abstract Observable<T> buildUseCaseObservable();

    public void execute(Subscriber<T> UseCaseSubscriber) {
        this.subscription = this.buildUseCaseObservable()
                .subscribeOn(Schedulers.from(mThreadExecutor))
                .observeOn(mPostExecutorThread.getScheduler())
                .subscribe(UseCaseSubscriber);
    }

    public void unsubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

}
