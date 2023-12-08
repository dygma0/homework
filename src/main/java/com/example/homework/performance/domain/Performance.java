package com.example.homework.performance.domain;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

import com.example.homework.user.domain.User;
import com.example.homework.venue.domain.Venue;
import jakarta.persistence.*;

@Entity
public class Performance {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "author_id")
  private User author;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "venue_id")
  private Venue venue;
}
