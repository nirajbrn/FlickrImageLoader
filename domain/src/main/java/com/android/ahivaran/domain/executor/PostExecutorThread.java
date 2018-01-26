package com.android.ahivaran.domain.executor;

import rx.Scheduler;

public interface PostExecutorThread {
  Scheduler getScheduler();
}
