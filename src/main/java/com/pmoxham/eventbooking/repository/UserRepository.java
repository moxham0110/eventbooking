package com.pmoxham.eventbooking.repository;

import com.pmoxham.eventbooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}