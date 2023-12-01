package com.example.homework.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionCause {
  JWT_EXPIRED(401, "JWT 토큰이 만료되었습니다."),
  JWT_INVALID(401, "JWT 토큰이 유효하지 않습니다.");

  private final int code;
  private final String message;
}
