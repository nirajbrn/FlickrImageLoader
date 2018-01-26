package com.android.ahivaran.data.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class GsonFactory {
  /**
   * Singleton static instance
   */
  private static Gson instance;

  public static void build() {
    if (instance != null) return;
    instance = new GsonBuilder().create();
  }

  /**
   * Getter for the singleton instance
   *
   * @return singleton instance of {@link Gson}
   */
  public static Gson getInstance() {
    if (instance == null) throw new RuntimeException("Call build() before you access the instance");
    return instance;
  }
}
