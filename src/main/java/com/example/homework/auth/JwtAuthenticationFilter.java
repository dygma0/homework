package com.example.homework.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final AuthenticationConverter authConverter = new JwtAuthenticationConverter();

  private final AuthenticationFailureHandler failureHandler =
      new JwtAuthenticationEntryPointFailureHandler();

  private SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder
      .getContextHolderStrategy();

  private AuthenticationManager authenticationManager;

  public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {
    try {
      Authentication token = this.authConverter.convert(request);
      if (token != null) {
        Authentication authResult = this.authenticationManager.authenticate(token);
        SecurityContext context = this.securityContextHolderStrategy.createEmptyContext();
        context.setAuthentication(authResult);
        this.securityContextHolderStrategy.setContext(context);
      }
    } catch (AuthenticationException ex) {
      failureHandler.onAuthenticationFailure(request, response, ex);
      return;
    }

    filterChain.doFilter(request, response);
  }
}
