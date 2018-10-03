package com.here.x;

import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

//using-pool
public class CompletableFuture04 {
  public static ExecutorService pool1 = Executors.newFixedThreadPool(1,
      new XThreadFactory("t1-"));
  static ForkJoinPool pool2 = new ForkJoinPool(6);

  public static void main(String[] args) throws Throwable {
    CompletableFuture04 ins = new CompletableFuture04();
    printIt("start");
    CompletableFuture.supplyAsync(() -> 3)
        .thenApplyAsync(ins::plus2, pool1)
        .thenApplyAsync(ins::x2, pool2)
        .thenApply(ins::x2)
        .thenApply(ins::x2)
        .thenApplyAsync(ins::plus2, pool1)
        .thenApply(ins::x2)
        .thenApply(ins::x2)
        .thenAccept((t) -> {
          printIt("final value : " + t);
          pool1.shutdown();
          pool2.shutdown();
        });
    // x.g
    printIt("end");
    Sleep.sleep(5000);

  }

  private static <T> T printIt(T i) {
    Sleep.sleep(1000);
    System.out.println(
        Timer.getLocalTimeString(LocalTime.now()) + " " + Thread.currentThread()
            .getName() + " -> " + i);
    return i;
  }

  private Integer x2(Integer i) {
    return printIt(i) * 2;
  }

  private Integer plus2(Integer i) {
    return printIt(i) + 2;
  }

}
