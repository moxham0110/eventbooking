package com.pmoxham.eventbooking.repository;

import com.pmoxham.eventbooking.model.Event;
import com.pmoxham.eventbooking.model.Registration;
import com.pmoxham.eventbooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByUser(User user);
    boolean existsByUserAndEvent(User user, Event event);
}
