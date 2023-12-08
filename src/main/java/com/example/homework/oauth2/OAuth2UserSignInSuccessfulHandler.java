package com.example.homework.oauth2;

import com.example.homework.user.application.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuth2UserSignInSuccessfulHandler implements AuthenticationSuccessHandler {

  private final UserService userService;

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException {
    if (authentication instanceof OAuth2AuthenticationToken token) {
      handleOAuth2User(request, response, token);
      return;
    }
    response.sendRedirect("/");
  }

  private void handleOAuth2User(
      HttpServletRequest request,
      HttpServletResponse response,
      OAuth2AuthenticationToken authentication) {
    String ACCESS_TOKEN_COOKIE_KEY = "access_token";
    String token = userService.login(authentication.getName());
    response.addCookie(new Cookie(ACCESS_TOKEN_COOKIE_KEY, token));
  }
}
