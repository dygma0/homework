package com.example.homework.venue.domain;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Venue {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
}
