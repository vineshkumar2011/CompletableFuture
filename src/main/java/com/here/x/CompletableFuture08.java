package com.here.x;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

//custom timeout using applytoEither
public class CompletableFuture08 {

  public static void main(String[] args) throws Throwable {
    CompletableFuture<Integer> timer = new CompletableFuture08()
        .timeout(Duration.ofSeconds(6));
    CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
      Sleep.sleep(1000);
      return 1;
    });
    CompletableFuture<Object> cf1 = cf.applyToEither(timer, x -> {
      return x;
    });
    try {
      System.out.println(cf1.get());
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(1);

  public CompletableFuture<Integer> timeout(Duration d) {
    CompletableFuture<Integer> cf = new CompletableFuture<>();
    scheduledPool.schedule(() -> {
      cf.completeExceptionally(new TimeoutException("what took you so long"));
    }, d.toMillis(), TimeUnit.MILLISECONDS);
    return cf;
  }

}
