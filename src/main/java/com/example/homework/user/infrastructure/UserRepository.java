package com.example.homework.user.infrastructure;

import com.example.homework.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

  @Query("SELECT u FROM User u WHERE u.username = :username")
  Optional<User> findByUsername(@Param("username") String username);
}
