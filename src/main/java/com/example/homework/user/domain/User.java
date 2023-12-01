package com.example.homework.user.domain;

import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = PROTECTED)
public class User {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private long id;

  private String username;

  public User(String username) {
    this.username = username;
  }
}
