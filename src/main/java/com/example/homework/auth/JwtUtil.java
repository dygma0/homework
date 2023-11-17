package com.example.homework.auth;

public class JwtUtil {

  private final String secret;

  private final long expiration;

  public JwtUtil(String secret, long expiration) {
    this.secret = secret;
    this.expiration = expiration;
  }

  public String generateToken(String username) {
    return username;
  }

  public String getUsername(String token) {
    return token;
  }
}
