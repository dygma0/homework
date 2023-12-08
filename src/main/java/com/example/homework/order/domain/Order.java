package com.example.homework.order.domain;

import com.example.homework.user.domain.User;
import jakarta.persistence.*;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
