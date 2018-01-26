package com.android.ahivaran.Presenter;

import com.android.ahivaran.domain.executor.PostExecutorThread;
import com.android.ahivaran.domain.executor.ThreadExecutor;
import com.android.ahivaran.domain.interactor.UseCase;
import com.android.ahivaran.executor.Executor;
import com.android.ahivaran.utils.CommonSubscriber;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

public abstract class Presenter {
  private static final String TAG = Presenter.class.getSimpleName();

  protected ThreadExecutor mThreadExecutor = new ThreadExecutor() {
    @Override public void execute(final Runnable command) {
      Executor.get().execute(command);
    }
  };

  protected PostExecutorThread mPostExecutorThread = new PostExecutorThread() {
    @Override public Scheduler getScheduler() {
      return AndroidSchedulers.mainThread();
    }
  };

  private List<UseCase> mUseCases = new CopyOnWriteArrayList<>();

  protected <T> void executeUseCase(final UseCase<T> useCase,
      final CommonSubscriber<T> useCaseSubscriber) {
    mUseCases.add(useCase);
    useCase.execute(useCaseSubscriber);
  }

  protected void unsubscribe() {
    Executor.get().execute(new Runnable() {
      @Override public void run() {
        for (UseCase useCase : mUseCases) {
          useCase.unsubscribe();
        }
      }
    });
  }

  // UI lifecycle events
  public abstract void resume();

  public abstract void pause();

  public abstract void destroy();
}