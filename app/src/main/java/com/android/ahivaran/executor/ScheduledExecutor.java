package com.android.ahivaran.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public final class ScheduledExecutor {
  private static final ScheduledExecutorService executor =
      Executors.newSingleThreadScheduledExecutor();

  public static ScheduledExecutorService get() {
    return executor;
  }
}
