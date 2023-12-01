package com.example.homework.auth;

import static org.junit.jupiter.api.Assertions.*;

import com.example.homework.common.StandardCalendar;
import org.junit.jupiter.api.Test;

class JwtProviderTest {

  private final JwtProvider provider =
      new JwtProvider(
          "rkGU45258GGhiolLO2465TFY5345kGU45258GGhiolLO2465TFY5345", 1000L, new StandardCalendar());

  @Test
  void generateToken() {
    // given
    final String subject = "test";

    // when
    final String token = provider.generateToken(subject);

    // then
    assertNotNull(token);
  }

  @Test
  void getSubject() {
    // given
    final String subject = "test";
    final String token = provider.generateToken(subject);

    // when
    final String result = provider.getSubject(token);

    // then
    assertEquals(subject, result);
  }
}
