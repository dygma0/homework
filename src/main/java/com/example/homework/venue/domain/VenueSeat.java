package com.example.homework.venue.domain;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.*;

@Entity
public class VenueSeat {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "venue_id")
  private Venue venue;
}
