package com.example.homework.oauth2;

import com.example.homework.user.application.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuth2UserSignInService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

  private final UserService userService;

  private final OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2Service =
      new DefaultOAuth2UserService();

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    final OAuth2User oauth2User = oauth2Service.loadUser(userRequest);
    final String email = getOAuth2UserEmail(oauth2User);
    userService.login(email);
    return null;
  }

  private String getOAuth2UserEmail(OAuth2User oauth2User) {
    return oauth2User.getAttribute("email");
  }
}
