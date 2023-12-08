package com.example.homework.config;

import com.example.homework.auth.JwtAuthenticationFilter;
import com.example.homework.auth.JwtAuthenticationProvider;
import com.example.homework.auth.JwtProvider;
import com.example.homework.oauth2.OAuth2UserSignInSuccessfulHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final OAuth2UserSignInSuccessfulHandler oAuth2UserSignInSuccessfulHandler;
  private final JwtProvider jwtProvider;

  @Bean
  public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
    return http.addFilterBefore(
            new JwtAuthenticationFilter(new JwtAuthenticationProvider(jwtProvider)),
            UsernamePasswordAuthenticationFilter.class)
        .oauth2Login(oauth2Config -> oauth2Config.successHandler(oAuth2UserSignInSuccessfulHandler))
        .formLogin(FormLoginConfigurer::disable)
        .csrf(CsrfConfigurer::disable)
        .sessionManagement(
            sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
        .build();
  }
}
