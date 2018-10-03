package com.here.x;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

//combine and compost , allof, eitherof
public class CompletableFuture09 {
  /*
   * compose
   */
  public static void main2(String[] args) throws Exception {
    CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
      Sleep.sleep(5000);
      return UUID.randomUUID()
          .toString();
    });
    // return type
    CompletableFuture<CompletableFuture<Long>> task2 = task1.thenApply((id) -> {
      CompletableFuture<Long> task3 = CompletableFuture.supplyAsync(() -> {
        return 34567890L;
      });
      return task3;
    });
    System.out.println(task2.get()
        .get());
    // OR we can use thenCombine
    CompletableFuture<Long> task4 = task1.thenCompose((id) -> {
      CompletableFuture<Long> task3 = CompletableFuture.supplyAsync(() -> {
        return 34567890L;
      });
      return task3;
    });
    System.out.println(task4.get());

  }

  // combine
  public static void main(String[] args) {
    final CompletableFuture<Integer> task1 = CompletableFuture
        .supplyAsync(() -> {
          return 2;
        });
    final CompletableFuture<Integer> task2 = CompletableFuture
        .supplyAsync(() -> {
          return 4;
        });

    try {
      task1.thenCombine(task2, (value1, value2) -> value1 + "_" + value2)
          .thenAccept(System.out::println);
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}
