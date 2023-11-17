package com.example.homework.config;

import com.example.homework.auth.JwtAuthenticationFilter;
import com.example.homework.auth.JwtAuthenticationProvider;
import com.example.homework.auth.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

  private final AuthenticationConfiguration authenticationConfiguration;

  public SecurityConfig(AuthenticationConfiguration authenticationConfiguration) {
    this.authenticationConfiguration = authenticationConfiguration;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    ProviderManager authenticationManager =
        (ProviderManager) authenticationConfiguration.getAuthenticationManager();
    authenticationManager.getProviders()
        .add(new JwtAuthenticationProvider(new JwtUtil("secret", 1000 * 60 * 60 * 24)));

    return http
        .addFilterBefore(new JwtAuthenticationFilter(authenticationManager),
            UsernamePasswordAuthenticationFilter.class)
        .authorizeHttpRequests(authorize ->
            authorize
                .requestMatchers("/api/**").authenticated()
                .anyRequest().permitAll()
        )
        .build();
  }
}
