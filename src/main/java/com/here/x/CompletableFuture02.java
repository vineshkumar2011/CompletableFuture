package com.here.x;

import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;

public class CompletableFuture02 {
  // chaining

  public static void main(String[] args) throws Throwable {
    CompletableFuture02 ins = new CompletableFuture02();
    printIt("start");
    CompletableFuture.supplyAsync(() -> 3)
        .thenApplyAsync(ins::plus2)
        .thenApplyAsync(ins::x2)
        .thenApply(ins::x2)
        .thenAccept((t) -> {
          printIt("final value : " + t);
        })
        .thenRun(() -> {
          throw new RuntimeException("2");
        })
        .handle((r, t) -> {
          System.out.println(t);
          System.out.println(r);
          return r;
        });
  }

  private static <T> T printIt(T i) {
    Sleep.sleep(100);
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
