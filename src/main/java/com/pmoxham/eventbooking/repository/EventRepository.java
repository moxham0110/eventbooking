package com.pmoxham.eventbooking.repository;

import com.pmoxham.eventbooking.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}