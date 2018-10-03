package com.here.x;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFuture07 {

  public static void main(String[] args)
      throws InterruptedException, ExecutionException {

    CompletableFuture<String> asyncGetCall = CompletableFuture06.get("123");
    asyncGetCall.thenApply(CompletableFuture07::printIt)
        .handle(CompletableFuture07::handleException);
  }

  private static <T> T printIt(T t) {
    System.out.println(t);
    return t;
  }

  private static String handleException(String res, Throwable t) {
    if (t == null) {
      System.out.println(res);
    } else {
      System.out.println("Error " + t.getMessage());
    }
    return res;
  }
}
