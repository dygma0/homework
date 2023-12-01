package com.example.homework.fixture;

import com.example.homework.user.domain.User;

public class UserFixture {

  public static User CREATE_TEST_USER() {
    return new User("test");
  }
}
