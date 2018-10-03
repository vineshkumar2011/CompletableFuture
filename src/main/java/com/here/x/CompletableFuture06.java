package com.here.x;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//async rest call, js promise equivaelnt in java
public class CompletableFuture06 {
  public static ExecutorService pool1 = Executors.newFixedThreadPool(3,
      new XThreadFactory("async1-"));

  static boolean throwException = true;

  static String getForEntity(String url) {
    if (throwException) {
      throw new RuntimeException("ConnectException");
    }
    return "success";
  }

  public static CompletableFuture<String> get(String url) {
    CompletableFuture<String> httpLoader = new CompletableFuture<String>();
    pool1.execute(() -> {
      try {
        String response = CompletableFuture06.getForEntity(url);
        httpLoader.complete(response);
      } catch (Exception e) {
        httpLoader.completeExceptionally(e);
      }
    });
    return httpLoader;
  }
}
