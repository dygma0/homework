package com.example.homework.common;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class StandardCalendar {

  public LocalDateTime now() {
    return LocalDateTime.now();
  }

  public Date toDate(LocalDateTime localDateTime) {
    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
  }
}
