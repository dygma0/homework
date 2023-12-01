package com.example.homework.auth;

import com.example.homework.common.StandardCalendar;
import com.example.homework.common.exception.AuthException;
import com.example.homework.common.exception.ExceptionCause;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

  private final SecretKey secretKey;
  private final Long accessExpirationTime;

  private final StandardCalendar calendar;

  public JwtProvider(
      @Value("${security.jwt.secret-key}") final String secretKey,
      @Value("${security.jwt.access-expiration-time}") final Long accessExpirationTime,
      StandardCalendar calendar) {
    this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    this.accessExpirationTime = accessExpirationTime;
    this.calendar = calendar;
  }

  public String generateToken(final String subject) {
    return createToken(subject, accessExpirationTime);
  }

  public String getSubject(final String token) {
    validateAccessToken(token);
    return parseToken(token).getBody().getSubject();
  }

  private Jws<Claims> parseToken(final String token) {
    return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
  }

  private String createToken(final String subject, final Long validityInMilliseconds) {
    final LocalDateTime now = calendar.now();
    final LocalDateTime expirationDateTime = now.plusSeconds(validityInMilliseconds);

    return Jwts.builder()
        .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
        .setSubject(subject)
        .setIssuedAt(calendar.toDate(now))
        .setExpiration(calendar.toDate(expirationDateTime))
        .signWith(secretKey, SignatureAlgorithm.HS256)
        .compact();
  }

  private void validateAccessToken(final String accessToken) {
    try {
      parseToken(accessToken);
    } catch (final ExpiredJwtException e) {
      throw new AuthException(ExceptionCause.JWT_EXPIRED);
    } catch (final JwtException | IllegalArgumentException e) {
      throw new AuthException(ExceptionCause.JWT_INVALID);
    }
  }
}
