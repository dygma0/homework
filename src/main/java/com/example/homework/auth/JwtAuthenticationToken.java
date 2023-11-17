package com.example.homework.auth;

import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class JwtAuthenticationToken implements Authentication {

  private final Collection<GrantedAuthority> authorities;

  private boolean authenticated;

  private final Object credentials;

  private Object details;

  private final Object principle;

  public JwtAuthenticationToken(Object credentials) {
    this.principle = null;
    this.credentials = credentials;
    this.authorities = Collections.emptyList();
    this.authenticated = false;
  }

  public JwtAuthenticationToken(Object principle, Collection<GrantedAuthority> authorities) {
    this.authorities = authorities;
    this.principle = principle;
    this.credentials = null;
    this.authenticated = true;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.unmodifiableCollection(this.authorities);
  }

  @Override
  public Object getCredentials() {
    return this.credentials;
  }

  @Override
  public Object getDetails() {
    return this.details;
  }

  @Override
  public Object getPrincipal() {
    return this.principle;
  }

  @Override
  public boolean isAuthenticated() {
    return this.authenticated;
  }

  @Override
  public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
    this.authenticated = isAuthenticated;
  }

  @Override
  public String getName() {
    if (this.getPrincipal() instanceof AuthenticatedPrincipal authenticatedPrincipal) {
      return authenticatedPrincipal.getName();
    }
    return (this.getPrincipal() == null) ? "" : this.getPrincipal().toString();
  }
}
