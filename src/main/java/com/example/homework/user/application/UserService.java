package com.example.homework.user.application;

import com.example.homework.auth.JwtProvider;
import com.example.homework.user.domain.User;
import com.example.homework.user.infrastructure.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

  private final UserRepository userRepository;
  private final JwtProvider jwtProvider;

  public String login(String username) {
    final User loggedInUser = findOrCreate(username);
    return jwtProvider.generateToken(loggedInUser.getUsername());
  }

  private User findOrCreate(String username) {
    final Optional<User> findUser = userRepository.findByUsername(username);
    return findUser.orElseGet(() -> userRepository.save(new User(username)));
  }
}
