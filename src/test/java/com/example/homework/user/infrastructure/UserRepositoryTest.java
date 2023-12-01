package com.example.homework.user.infrastructure;

import static org.junit.jupiter.api.Assertions.*;

import com.example.homework.fixture.UserFixture;
import com.example.homework.user.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@SuppressWarnings("NonAsciiCharacters")
class UserRepositoryTest {

  @Autowired private UserRepository userRepository;

  @Test
  void 유저_저장() {
    // Given
    final User user = UserFixture.CREATE_TEST_USER();

    // When
    final User createdUser = userRepository.save(user);

    // Then
    assertEquals(user, createdUser);
  }

  @Test
  void 유저_닉네임_조회() {
    // Given
    final User user = UserFixture.CREATE_TEST_USER();
    userRepository.save(user);

    // When
    final User foundUser = userRepository.findByUsername(user.getUsername()).orElseThrow();

    // Then
    assertEquals(user, foundUser);
  }
}
