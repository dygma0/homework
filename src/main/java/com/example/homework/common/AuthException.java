package com.example.homework.common;

import lombok.Getter;

@Getter
public class AuthException extends RuntimeException {

  private final int code;
  private final String message;

  public AuthException(final ExceptionCause cause) {
    super(cause.getMessage());
    this.code = cause.getCode();
    this.message = cause.getMessage();
  }
}
