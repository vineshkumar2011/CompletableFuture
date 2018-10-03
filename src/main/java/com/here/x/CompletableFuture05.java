package com.here.x;

import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

//exception-handling
public class CompletableFuture05 {

  static AtomicInteger counter = new AtomicInteger(0);

  public static void main3(String[] args) throws Throwable {
    CompletableFuture<Integer> x = CompletableFuture.supplyAsync(() -> {
      Sleep.sleep(2000);
      return 3;
    });
    x.completeExceptionally(new Throwable("123"));
    System.out.println(x.get());
  }

  public static void main(String[] args) {
    CompletableFuture05 instance = new CompletableFuture05();
    printIt("start");
    CompletableFuture<Void> x = CompletableFuture.supplyAsync(() -> 3)
        .thenApplyAsync(instance::plus2)
        .thenApplyAsync(instance::x2)
        .thenApply(instance::plus2)
        .exceptionally((Throwable t) -> {
          throw new RuntimeException("1");
        })
        .thenApply(instance::plus2)
        .thenApply(instance::x2)
        .thenAccept((t) -> {
          printIt("final value : " + t);
        });
    x.handle((response, throwable) -> {
      if (throwable == null) {
        // no error
        System.out.println(response);
      } else {
        throw new RuntimeException(throwable);
      }
      return -1;
    });
    Sleep.sleep(100);
  }

  private static <T> T printIt(T i) {
    if (counter.getAndIncrement() == 2) {
      throw new RuntimeException("error");
    }
    Sleep.sleep(1000);
    System.out.println(
        Timer.getLocalTimeString(LocalTime.now()) + " " + Thread.currentThread()
            .getName() + " -> " + i);
    return i;
  }

  private Integer x2(Integer i) {
    System.out.println(LocalTime.now() + " " + Thread.currentThread()
        .getName() + " -> x2");
    return printIt(i) * 2;
  }

  private Integer plus2(Integer i) {
    System.out.println(LocalTime.now() + " " + Thread.currentThread()
        .getName() + " -> plus2");
    return printIt(i) + 2;
  }

}
