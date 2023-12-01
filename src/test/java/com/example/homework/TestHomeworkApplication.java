package com.example.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestHomeworkApplication {

  public static void main(String[] args) {
    SpringApplication.from(HomeworkApplication::main).with(TestHomeworkApplication.class).run(args);
  }
}
