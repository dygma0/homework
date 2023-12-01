package com.example.homework.user.presentation;

import com.example.homework.decorator.Authenticated;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserHttpApiController {

  @Authenticated
  @RequestMapping("/me")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<String> me(@AuthenticationPrincipal String username) {
    return ResponseEntity.ok(username);
  }

  @RequestMapping("/{username}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<String> getUser(@PathVariable String username) {
    return ResponseEntity.ok(username);
  }
}
