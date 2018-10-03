package com.here.x;

import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//Future vs CompletableFuture
public class CompletableFuture03 {
  public static ExecutorService pool = Executors.newFixedThreadPool(4);

  public static void main(String[] args) throws Throwable {
    System.out.println(new Date());
    Future<Integer> future = pool.submit(() -> {
      System.out.println(LocalTime.now() + " " + " " + Thread.currentThread()
          + "_CompletableFuture03#main");
      Sleep.sleep(3000);
      return 2;
    });

    // misisng in Completable Future, but possible
    // System.out.println("get in second:" + future.get(1, TimeUnit.SECONDS));
    while (future.isDone() == false) {
      Sleep.sleep(100);
    }
    System.out.println("get:" + future.get());
    System.out.println(new Date());
    // misisng in Completable Future
    // System.out.println("cancel:" + future.cancel(true));

  }

  public static void main2(String[] args) throws Throwable {
    CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
      // Sleep.sleep(10000);
      // Throw
      return 1;
    });
    // Sleep.sleep(100);
    System.out.println(cf.getNow(-1));
  }

}
