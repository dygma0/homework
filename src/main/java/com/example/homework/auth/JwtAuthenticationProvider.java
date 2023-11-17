package com.example.homework.auth;

import java.util.Collections;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationProvider implements AuthenticationProvider {

  private final JwtUtil jwtUtil;

  public JwtAuthenticationProvider(JwtUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    try {
      JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
      String token = (String) jwtAuthenticationToken.getCredentials();
      String username = jwtUtil.getUsername(token);
      return new JwtAuthenticationToken(username, Collections.emptyList());
    } catch (JwtAuthenticationException e) {
      throw e;
    } catch (Exception e) {
      throw new JwtAuthenticationException("JWT token is incorrect");
    }
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return JwtAuthenticationToken.class.isAssignableFrom(authentication);
  }
}
