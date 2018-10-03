package com.here.x;

import java.util.stream.LongStream;

public class Busy {
  public static void busy500ms() {
    LongStream.range(1, 999999L)
        .forEach((i) -> {
          busy0();
        });
  }

  public static void busy2s() {
    busy1s();
    busy1s();
  }

  public static void busy1s() {
    busy500ms();
    busy500ms();
  }

  private static void busy0() {
    Math.tan(Math.atan(Math.tan(Math.atan(Math.tan(Math.atan(
        Math.tan(Math.atan(Math.tan(Math.atan(123456789.123456789))))))))));
  }

}
