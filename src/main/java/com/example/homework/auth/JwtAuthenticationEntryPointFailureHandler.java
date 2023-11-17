package com.example.homework.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class JwtAuthenticationEntryPointFailureHandler implements AuthenticationFailureHandler {

  private final AuthenticationEntryPoint entryPoint = new JwtAuthenticationEntryPoint();

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                      AuthenticationException exception)
      throws IOException, ServletException {
    entryPoint.commence(request, response, exception);
  }
}
