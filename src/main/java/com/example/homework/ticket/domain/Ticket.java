package com.example.homework.ticket.domain;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

import com.example.homework.user.domain.User;
import com.example.homework.venue.domain.VenueSeat;
import jakarta.persistence.*;

@Entity
public class Ticket {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "venue_seat_id")
  private VenueSeat venueSeat;
}
