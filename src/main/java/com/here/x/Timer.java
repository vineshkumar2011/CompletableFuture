package com.here.x;

import java.time.Duration;
import java.time.LocalTime;

public class Timer {

  final LocalTime start;

  private Timer() {
    start = LocalTime.now();
  }

  public static Timer start() {
    return new Timer();
  }

  public String end() {
    return Duration.between(LocalTime.now(), start)
        .toString();
  }

  public static String getLocalTimeString(LocalTime time) {
    StringBuilder buf = new StringBuilder(18);
    int hourValue = time.getHour();
    int minuteValue = time.getMinute();
    int secondValue = time.getSecond();
    int nanoValue = time.getNano();
    buf.append(hourValue < 10 ? "0" : "")
        .append(hourValue)
        .append(minuteValue < 10 ? ":0" : ":")
        .append(minuteValue);
    if (secondValue > 0 || nanoValue > 0) {
      buf.append(secondValue < 10 ? ":0" : ":")
          .append(secondValue);
      if (nanoValue > 0) {
        buf.append('.');
        if (nanoValue % 1000_000 == 0) {
          buf.append(Integer.toString((nanoValue / 1000_000) + 1000)
              .substring(1));
        } else if (nanoValue % 1000 == 0) {
          buf.append(Integer.toString((nanoValue / 1000) + 1000_000)
              .substring(1));
        } else {
          buf.append(Integer.toString((nanoValue) + 1000_000_000)
              .substring(1));
        }
      } else {
        buf.append(".000");
      }
    }
    return buf.toString();
  }
}
