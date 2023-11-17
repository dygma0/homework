package com.example.homework.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/users")
@RestController
public class UserHttpApiController {

  @RequestMapping("/me")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<String> me(@AuthenticationPrincipal String username) {
    return ResponseEntity.ok(username);
  }
}
