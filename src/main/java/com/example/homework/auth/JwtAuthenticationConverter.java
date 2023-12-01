package com.example.homework.auth;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.authentication.AuthenticationConverter;

public class JwtAuthenticationConverter implements AuthenticationConverter {

  private static final String JWT_AUTHORIZATION_HEADER_NAME = "Authorization";

  private static final String JWT_PREFIX = "Bearer ";

  private static final int JWT_PREFIX_LENGTH = JWT_PREFIX.length();

  @Override
  public JwtAuthenticationToken convert(HttpServletRequest request) {
    final String tokenHeader = request.getHeader(JWT_AUTHORIZATION_HEADER_NAME);
    if (tokenHeader != null && tokenHeader.startsWith(JWT_PREFIX)) {
      final String token = tokenHeader.substring(JWT_PREFIX_LENGTH);
      return new JwtAuthenticationToken(token);
    }

    return null;
  }
}
