package com.example.homework.user.application;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.homework.auth.JwtProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@SuppressWarnings("NonAsciiCharacters")
class UserServiceIntegrationTest {

  @Autowired private UserService userService;

  @Autowired private JwtProvider jwtProvider;

  @Test
  void 유저_로그인() {
    // given
    final String username = "test";

    // when
    final String token = userService.login(username);

    // then
    assertThat(token).isNotBlank();
    assertThat(jwtProvider.getSubject(token)).isEqualTo(username);
  }
}
