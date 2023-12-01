package com.example.homework.config;

import com.example.homework.auth.JwtAuthenticationFilter;
import com.example.homework.auth.JwtAuthenticationProvider;
import com.example.homework.auth.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
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

  private final AuthenticationConfiguration authenticationConfiguration;
  private final JwtProvider jwtProvider;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    ProviderManager authenticationManager =
        (ProviderManager) authenticationConfiguration.getAuthenticationManager();
    authenticationManager.getProviders().add(new JwtAuthenticationProvider(jwtProvider));

    return http.addFilterBefore(
            new JwtAuthenticationFilter(authenticationManager),
            UsernamePasswordAuthenticationFilter.class)
        .formLogin(FormLoginConfigurer::disable)
        .csrf(CsrfConfigurer::disable)
        .sessionManagement(
            sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
        .build();
  }
}
