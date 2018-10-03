package com.here.x;

import java.util.concurrent.CompletableFuture;

/*
 * introduction : CompletableFuture01
 * Future: CompletableFuture03
 * chaining : CompletableFuture02
 * using-pool : CompletableFuture04
 * exception-handling : CompletableFuture05
 * js promise : CompletableFuture06, CompletableFuture07
 * write you own timeout : CompletableFuture08
 * combine compost : CompletableFuture09
 */
public class CompletableFuture01 {
  public static void main2(String[] args) throws Throwable {
    CompletableFuture<String> cf = CompletableFuture
        .completedFuture("hello-world");
    System.out.println(cf.get());
    System.out.println(cf.getNow("timeout"));
  }

  public static void main(String[] args) throws InterruptedException {
    CompletableFuture.supplyAsync(() -> {
      System.out.println("hello world in CompletableFuture");
      return 1;
    });
    Thread.sleep(100);
  }

  public static void main3(String[] args) {
    CompletableFuture<String> cf = new CompletableFuture<String>();
    cf.complete("hello_world");
  }
}
