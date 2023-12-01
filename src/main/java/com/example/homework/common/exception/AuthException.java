package com.example.homework.common.exception;

public class AuthException extends RuntimeException {

  private final int code;
  private final String message;

  public AuthException(final ExceptionCause cause) {
    this.code = cause.getCode();
    this.message = cause.getMessage();
  }
}
