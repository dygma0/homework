package com.example.homework.auth;

import java.util.Collections;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationProvider implements AuthenticationProvider {

  private final JwtProvider jwtProvider;

  public JwtAuthenticationProvider(final JwtProvider jwtProvider) {
    this.jwtProvider = jwtProvider;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    try {
      return jwtAuthenticate(authentication);
    } catch (JwtAuthenticationException e) {
      throw e;
    } catch (Exception e) {
      throw new JwtAuthenticationException("Unkonw jwt authentication error", e);
    }
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return JwtAuthenticationToken.class.isAssignableFrom(authentication);
  }

  private JwtAuthenticationToken jwtAuthenticate(Authentication authentication) {
    if (authentication instanceof JwtAuthenticationToken authenticationToken) {
      return verifyJwtAuthenticationToken(authenticationToken);
    }
    throw new JwtAuthenticationException("JWT token is incorrect");
  }

  private JwtAuthenticationToken verifyJwtAuthenticationToken(
      JwtAuthenticationToken authentication) {
    String token = (String) authentication.getCredentials();
    String username = jwtProvider.getSubject(token);
    return new JwtAuthenticationToken(username, Collections.emptyList());
  }
}
